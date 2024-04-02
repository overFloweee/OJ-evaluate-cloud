package com.hjw.question.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjw.api.service.JudgeClient;
import com.hjw.api.service.UserFeignClient;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.constant.CommonConstant;
import com.hjw.common.exception.BusinessException;
import com.hjw.common.utils.SqlUtils;
import com.hjw.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hjw.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.QuestionSubmit;
import com.hjw.model.entity.User;
import com.hjw.model.enums.JudgeHistoryEnum;
import com.hjw.model.enums.JudgeInfoEnum;
import com.hjw.model.enums.QuestionSubmitLanguageEnum;
import com.hjw.model.enums.QuestionSubmitStatusEnum;
import com.hjw.model.vo.QuestionSubmitVO;
import com.hjw.model.vo.QuestionVO;
import com.hjw.model.vo.UserVO;
import com.hjw.question.mapper.QuestionSubmitMapper;
import com.hjw.question.rabbitmq.MyMessageProducer;
import com.hjw.question.service.QuestionService;
import com.hjw.question.service.QuestionSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86157
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-01-22 21:50:08
 */
@Service
@RequiredArgsConstructor
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService
{

    @Resource
    @Lazy
    private final QuestionService questionService;
    private final UserFeignClient userFeignClient;
    private final HttpServletRequest request;
    @Resource
    @Lazy
    private JudgeClient judgeClient;

    private final MyMessageProducer myMessageProducer;


    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest)
    {
        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();

        if (questionSubmitAddRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 校验编程语言是否合法
        String language = questionSubmitAddRequest.getSubmitLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }

        // 判断题目是否存在
        long questionId = questionSubmitAddRequest.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 复制部分字段属性
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtil.copyProperties(questionSubmitAddRequest, questionSubmit);
        // 填充信息
        questionSubmit.setUserId(userId);
        // 初始化状态
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setJudgeInfo("{}");
        questionSubmit.setLanguage(questionSubmitAddRequest.getSubmitLanguage());
        questionSubmit.setBackendCode(questionSubmitAddRequest.getSubmitCode());

        boolean isSave = this.save(questionSubmit);
        if (!isSave)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "用户提交失败");
        }
        // 提交到消息队列
        Long questionSubmitId = questionSubmit.getId();
        myMessageProducer.sendMessage("code_exchange", "my_routingKey", questionSubmitId.toString());

        // CompletableFuture.runAsync(() ->
        // {
        //     judgeClient.doJudge(questionSubmitId);
        // });

        return questionSubmitId;
    }


    /**
     * 获取查询包装类（根据用户传递的查询包装类，生成mybatis支持的查询类）
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest)
    {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();


        queryWrapper.lambda().eq(StrUtil.isNotBlank(language), QuestionSubmit::getLanguage, language);
        queryWrapper.lambda()
                .like(QuestionSubmitStatusEnum.getEnumByValue(status) != null, QuestionSubmit::getStatus, status);
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(questionId), QuestionSubmit::getQuestionId, questionId);
        queryWrapper.lambda().like(ObjectUtil.isNotEmpty(userId), QuestionSubmit::getUserId, userId);
        queryWrapper.lambda().eq(QuestionSubmit::getIsDelete, false);
        // 根据 查询条件进行排序 ，sortField - 需要排序的字段， sortOrder - 排序方式 asc、desc
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField
        );

        return queryWrapper;
    }


    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser)
    {
        long userId = loginUser.getId();

        // 1. questionSubmit 转 vo
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);


        // 根据用户信息，返回相应的 脱敏信息
        //     不是两者，则需要脱敏
        if (!userFeignClient.isAdmin(loginUser) && !questionSubmit.getUserId().equals(userId))
        {
            questionSubmitVO.setFrontendCode(null);
        }


        // todo 填充 QuestionSubmitVO 的 UserVO 和 QuestionVO

        return questionSubmitVO;
    }


    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage)
    {
        // 获取当前用户
        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();

        // 取出信息
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        long current = questionSubmitPage.getCurrent();
        long total = questionSubmitPage.getTotal();
        long size = questionSubmitPage.getSize();
        // 填充page信息
        Page<QuestionSubmitVO> questionVOPage = new Page<>(current, size, total);


        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(questionSubmit ->
        {
            QuestionSubmitVO questionSubmitVO = this.getQuestionSubmitVO(questionSubmit, loginUser);
            // 封装userVO
            User user = userFeignClient.getById(questionSubmit.getUserId());
            UserVO userVO = userFeignClient.getUserVO(user);
            questionSubmitVO.setUserVO(userVO);
            // 封装questionVO
            Question question = questionService.getById(questionSubmit.getQuestionId());
            QuestionVO questionVO = questionService.getQuestionVO(question);
            questionSubmitVO.setQuestionVO(questionVO);
            return questionSubmitVO;
        }).collect(Collectors.toList());
        questionVOPage.setRecords(questionSubmitVOList);

        return questionVOPage;

    }


    @Override
    public JudgeHistoryEnum queryHistoryJudge(long questionId)
    {
        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();

        LambdaQueryWrapper<QuestionSubmit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(QuestionSubmit::getQuestionId, questionId);
        wrapper.eq(QuestionSubmit::getUserId, userId);
        List<QuestionSubmit> list = this.list(wrapper);

        if (CollectionUtils.isEmpty(list))
        {
            // 无提交记录
            return JudgeHistoryEnum.NO_TRIED;
        }

        for (QuestionSubmit questionSubmit : list)
        {
            String judgeInfo = questionSubmit.getJudgeInfo();
            if (judgeInfo.contains(JudgeInfoEnum.ACCEPTED.getValue()))
            {
                // 有一个通过记录，则通过
                return JudgeHistoryEnum.ACCEPTED;
            }
        }

        // 尝试过
        return JudgeHistoryEnum.HAVE_TRIED;
    }
}




