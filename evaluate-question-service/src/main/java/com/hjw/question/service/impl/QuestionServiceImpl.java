package com.hjw.question.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjw.api.service.UserFeignClient;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.constant.CommonConstant;
import com.hjw.common.exception.BusinessException;
import com.hjw.common.exception.ThrowUtils;
import com.hjw.common.utils.SqlUtils;
import com.hjw.model.dto.question.JudgeCase;
import com.hjw.model.dto.question.QuestionAddRequest;
import com.hjw.model.dto.question.QuestionEditRequest;
import com.hjw.model.dto.question.QuestionQueryRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.User;
import com.hjw.model.vo.QuestionVO;
import com.hjw.model.vo.UserVO;
import com.hjw.question.mapper.QuestionMapper;
import com.hjw.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 86157
 * @description 针对表【question(题目)】的数据库操作Service实现
 * @createDate 2024-01-22 21:50:08
 */
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService
{

    private final UserFeignClient userFeignClient;
    private final HttpServletRequest request;


    @Override
    public Long addQuestion(QuestionAddRequest questionAddRequest)
    {
        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();


        // 转换成标准 question
        Question question = BeanUtil.copyProperties(questionAddRequest, Question.class, "tags", "judgeConfig",
                "judgeCase"
        );
        // 填充信息
        question.setUserId(userId);
        question.setTags(JSONUtil.toJsonStr(questionAddRequest.getTags()));
        question.setJudgeCase(JSONUtil.toJsonStr(questionAddRequest.getJudgeCase()));
        question.setJudgeConfig(JSONUtil.toJsonStr(questionAddRequest.getJudgeConfig()));

        this.validQuestion(question, true);


        boolean save = this.save(question);
        if (!save)
        {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "新增题目失败");
        }
        return question.getId();
    }

    /**
     * 校验题目信息是否合法
     *
     * @param question
     * @param add
     */
    @Override
    public void validQuestion(Question question, boolean add)
    {
        if (question == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String title = question.getTitle();
        String content = question.getContent();
        String answer = question.getAnswer();
        String tags = question.getTags();
        String judgeCase = question.getJudgeCase();
        String judgeConfig = question.getJudgeConfig();

        // 信息校验
        // 如果是添加时，参数不能为空
        if (add && StringUtils.isAnyBlank(title, content, tags, answer, judgeCase, judgeConfig))
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 有参数则继续校验
        if (StringUtils.isNotBlank(title) && title.length() > 80)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "标题过长");
        }
        if (StringUtils.isNotBlank(content) && content.length() > 8192)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (StringUtils.isNotBlank(answer) && answer.length() > 8192)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "答案过长");
        }
        if (StringUtils.isNotBlank(judgeCase) && judgeCase.length() > 8192)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题用例过长");
        }
        if (StringUtils.isNotBlank(judgeConfig) && judgeConfig.length() > 8192)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "判题配置过长");
        }


    }


    /**
     * 获取查询包装类（根据用户传递的查询包装类，生成mybatis支持的查询类）
     */
    @Override
    public LambdaQueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest)
    {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        if (questionQueryRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long id = questionQueryRequest.getId();
        String title = questionQueryRequest.getTitle();
        String content = questionQueryRequest.getContent();
        String answer = questionQueryRequest.getAnswer();
        List<String> tags = questionQueryRequest.getTags();
        Long userId = questionQueryRequest.getUserId();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();


        queryWrapper.eq(ObjectUtil.isNotEmpty(id), Question::getId, id);
        queryWrapper.like(StrUtil.isNotBlank(title), Question::getTitle, title);
        queryWrapper.like(StrUtil.isNotBlank(content), Question::getContent, content);
        queryWrapper.like(StrUtil.isNotBlank(answer), Question::getAnswer, answer);
        queryWrapper.eq(Question::getIsDelete,false);

        if (CollectionUtil.isNotEmpty(tags))
        {
            for (String tag : tags)
            {
                queryWrapper.like(Question::getTags, "\"" + tag + "\"");
            }
        }
        queryWrapper.eq(ObjectUtil.isNotEmpty(userId), Question::getUserId, userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                question -> sortField
        );

        return queryWrapper;
    }


    @Override
    public QuestionVO getQuestionVO(Question question)
    {
        // 1. question 转 vo
        QuestionVO questionVO = QuestionVO.objToVo(question);

        // 2.查询关联用户信息
        Long userId = question.getUserId();
        User user = null;
        if (userId != null && userId > 0)
        {
            user = userFeignClient.getById(userId);
        }

        UserVO userVO = userFeignClient.getUserVO(user);
        questionVO.setUserVO(userVO);

        return questionVO;
    }


    @Override
    public Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage)
    {
        // 取出信息
        List<Question> questionList = questionPage.getRecords();
        long current = questionPage.getCurrent();
        long total = questionPage.getTotal();
        long size = questionPage.getSize();
        // 填充page信息
        Page<QuestionVO> questionVOPage = new Page<>(current, size, total);

        // 查询关联用户信息
        // 转VO
        List<QuestionVO> questionVOlist = questionList.stream().map(this::getQuestionVO).collect(Collectors.toList());

        questionVOPage.setRecords(questionVOlist);

        return questionVOPage;

    }


    @Override
    public boolean editQuestion(QuestionEditRequest questionEditRequest)
    {
        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();

        // 判断题目是否存在
        Long questionId = questionEditRequest.getId();
        Question oldQuestion = this.getById(questionId);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);

        // 仅本人或管理员可编辑
        if (!oldQuestion.getUserId().equals(userId) && !userFeignClient.isAdmin(loginUser))
        {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        Question question = new Question();
        BeanUtils.copyProperties(questionEditRequest, question);

        // 字段填充和修改
        List<String> tags = questionEditRequest.getTags();
        List<JudgeCase> judgeCase = questionEditRequest.getJudgeCase();
        List<JudgeCase> judgeConfig = questionEditRequest.getJudgeConfig();
        if (tags != null)
        {
            question.setTags(JSONUtil.toJsonStr(tags));
        }
        if (judgeCase != null)
        {
            question.setJudgeCase(JSONUtil.toJsonStr(judgeCase));
        }
        if (judgeConfig != null)
        {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfig));
        }

        // 参数校验
        this.validQuestion(question, false);

        return this.updateById(question);

    }
}




