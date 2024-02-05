package com.hjw.judge.strategy.impl;

import cn.hutool.json.JSONUtil;
import com.hjw.judge.strategy.JudgeContext;
import com.hjw.judge.strategy.JudgeStrategy;
import com.hjw.model.dto.question.JudgeCase;
import com.hjw.model.dto.question.JudgeConfig;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.model.entity.Question;
import com.hjw.model.enums.JudgeInfoEnum;

import java.util.List;


/**
 * 默认 判题策略
 *
 * @return
 */
public class DefaultJudgeStrategyImpl implements JudgeStrategy
{

    /**
     * 判默认判题策略，去返回相应 判题信息
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext)
    {
        JudgeInfo judgeInfoResponse = new JudgeInfo();

        // 获取 运行 的输入信息
        List<String> inputList = judgeContext.getInputList();
        // 获取 运行 的输出结果
        List<String> outputList = judgeContext.getOutputList();
        // 获取 运行 的信息
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        Question question = judgeContext.getQuestion();

        // 获取程序执行，消耗的时间和内存信息
        Long time = judgeInfo.getTime();
        Long memory = judgeInfo.getMemory();
        // 填充返回结果
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);


        // 根据结果，设置 判题的 信息和状态
        JudgeInfoEnum judgeInfoEnum = JudgeInfoEnum.ACCEPTED;
        // 判断 输入输出 个数
        if (outputList.size() != inputList.size())
        {
            // 答案错误
            judgeInfoEnum = JudgeInfoEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoEnum.getValue());
            return judgeInfoResponse;
        }

        for (int i = 0; i < judgeCaseList.size(); i++)
        {
            JudgeCase judgeCase = judgeCaseList.get(i);
            // 与正确答案 的输出结果 不符
            if (!judgeCase.getOutput().equals(outputList.get(i)))
            {
                // 答案错误
                judgeInfoEnum = JudgeInfoEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoEnum.getValue());
                return judgeInfoResponse;
            }
        }

        // 判断题目限制
        String limitConfig = question.getJudgeConfig();
        JudgeConfig limitJudgeConfig = JSONUtil.toBean(limitConfig, JudgeConfig.class);
        Long memoryLimit = limitJudgeConfig.getMemoryLimit();
        Long timeLimit = limitJudgeConfig.getTimeLimit();


        if (time > timeLimit)
        {
            judgeInfoEnum = JudgeInfoEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoEnum.getValue());
            return judgeInfoResponse;
        }
        if (memory > memoryLimit)
        {
            judgeInfoEnum = JudgeInfoEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoEnum.getValue());
            return judgeInfoResponse;
        }

        // 若无上述报错，则 accepted
        judgeInfoResponse.setMessage(judgeInfoEnum.getValue());
        return judgeInfoResponse;


    }
}
