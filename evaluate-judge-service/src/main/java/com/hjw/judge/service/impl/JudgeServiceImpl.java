package com.hjw.judge.service.impl;

import cn.hutool.json.JSONUtil;
import com.hjw.api.service.QuestionFeignClient;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.exception.BusinessException;
import com.hjw.judge.sandbox.CodeSandbox;
import com.hjw.judge.sandbox.CodeSandboxFactory;
import com.hjw.judge.sandbox.CodeSandboxProxy;
import com.hjw.judge.strategy.JudgeManage;
import com.hjw.judge.service.JudgeService;
import com.hjw.judge.strategy.JudgeContext;
import com.hjw.model.dto.question.JudgeCase;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.QuestionSubmit;
import com.hjw.model.enums.QuestionSubmitStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudgeServiceImpl implements JudgeService
{

    private final QuestionFeignClient questionFeignClient;


    private final JudgeManage judgeManage;

    private final CodeSandboxFactory codeSandboxFactory;

    @Value("${codesandbox.type:example}")
    private String type;


    @Override
    public QuestionSubmit doJudge(long questionSubmitId)
    {

        // 提交代码的提交id，获取到对应题目、提交信息
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "提交信息不存在");
        }

        // 如果判题状态不为 等待中，说明该题无需再判
        Integer status = questionSubmit.getStatus();
        if (!QuestionSubmitStatusEnum.WAITING.getValue().equals(status))
        {
            // throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题中");
        }

        // 判断题目是否存在
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionFeignClient.getQuestionById(questionId);
        if (question == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }

        // 准备就绪，开始判题，先修改判题状态 -> 1 - 判题中
        QuestionSubmit updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setStatus(1);
        updateQuestionSubmit.setId(questionSubmitId);
        boolean isUpdate = questionFeignClient.updateQuestionSubmitById(updateQuestionSubmit);
        if (!isUpdate)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }


        // 调用沙盒，获取 运行信息
        String code = questionSubmit.getCode();
        String language = questionSubmit.getLanguage();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        // 获取输入用例
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());

        // 获取沙盒的执行结果
        ExecuteCodeResponse executeCodeResponse = this.doSubmitCodeSandbox(code, language, inputList);

        // 根据不同的语言，修改判题策略

        // 利用 默认的判题策略 去判断运行信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(executeCodeResponse.getOutputList());
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeInfo judgeInfo = judgeManage.doJudge(judgeContext);

        // 修改数据库的判题状态 和 信息
        QuestionSubmit judgedUpdateQuestionSubmit = new QuestionSubmit();
        judgedUpdateQuestionSubmit.setId(questionSubmitId);
        judgedUpdateQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        judgedUpdateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        boolean judgedUpdate = questionFeignClient.updateQuestionSubmitById(judgedUpdateQuestionSubmit);
        if (!judgedUpdate)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新错误");
        }

        // 查询最新状态
        return questionFeignClient.getQuestionSubmitById(questionSubmitId);
    }

    private ExecuteCodeResponse doSubmitCodeSandbox(String code, String language, List<String> inputList)
    {
        // 生成代码沙箱
        CodeSandbox codeSandbox = codeSandboxFactory.newInstance(type);
        // 生成代码沙箱 代理类
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);

        // 构造 判题需要的 请求信息
        ExecuteCodeRequest build = ExecuteCodeRequest.builder().code(code).language(language).inputList(inputList)
                .build();

        // 代理类 执行原始方法
        return codeSandboxProxy.executeCode(build);
    }
}
