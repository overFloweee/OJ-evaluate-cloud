package com.hjw.question.controller;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.api.service.UserFeignClient;
import com.hjw.common.annotation.AuthCheck;
import com.hjw.common.common.DeleteRequest;
import com.hjw.common.common.ErrorCode;
import com.hjw.common.common.R;
import com.hjw.common.constant.UserConstant;
import com.hjw.common.exception.BusinessException;
import com.hjw.common.exception.ThrowUtils;
import com.hjw.model.dto.question.QuestionAddRequest;
import com.hjw.model.dto.question.QuestionEditRequest;
import com.hjw.model.dto.question.QuestionQueryRequest;
import com.hjw.model.dto.question.QuestionUpdateRequest;
import com.hjw.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.hjw.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.hjw.model.dto.questionsubmit.QuestionSubmitUpdateRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.QuestionSubmit;
import com.hjw.model.entity.User;
import com.hjw.model.vo.QuestionSubmitVO;
import com.hjw.model.vo.QuestionVO;
import com.hjw.question.service.QuestionService;
import com.hjw.question.service.QuestionSubmitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class QuestionController
{

    private final QuestionService questionService;
    private final UserFeignClient userFeignClient;
    private final HttpServletRequest request;
    private final QuestionSubmitService questionSubmitService;

    /**
     * 根据 id 获取题目信息（脱敏）
     *
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public R<QuestionVO> getQuestionVOById(Long id)
    {
        questionSubmitService.getById(null);
        if (id == null || id <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Question question = questionService.getById(id);
        if (question == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        QuestionVO questionVO = questionService.getQuestionVO(question);

        return R.success(questionVO);
    }

    @PostMapping("/add")
    public R<Long> addQuestion(@RequestBody QuestionAddRequest questionAddRequest)
    {
        if (questionAddRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Long questionId = questionService.addQuestion(questionAddRequest);
        return R.success(questionId);
    }


    /**
     * 分页获取列表（封装类）
     *
     * @param questionQueryRequest
     * @return
     */
    @PostMapping("/list/page/vo")
    public R<Page<QuestionVO>> listQuestionVOByPage(@RequestBody QuestionQueryRequest questionQueryRequest)
    {
        // 获取分页数据
        long pageNum = questionQueryRequest.getCurrent();
        long pageSize = questionQueryRequest.getPageSize();

        // 进行分页查询 题目信息
        Page<Question> questionPage = questionService.page(new Page<>(pageNum, pageSize),
                questionService.getQueryWrapper(questionQueryRequest)
        );
        // 转化题目类 成包装类

        List<QuestionVO> records = questionPage.getRecords().stream().map(questionService::getQuestionVO)
                .collect(Collectors.toList());
        // 获取并填充分页对象
        Page<QuestionVO> page = new Page<>(pageNum, pageSize, questionPage.getTotal());
        page.setRecords(records);
        return R.success(page);
    }


    /**
     * 删除（仅管理员或本人）
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Boolean> deleteQuestion(@PathVariable("id") Long id)
    {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.NOT_FOUND_ERROR);

        // 判断是否存在
        Question oldQuestion = questionService.getById(id);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);

        User loginUser = userFeignClient.getLoginUser(request);
        Long userId = loginUser.getId();
        // 判断是否是 管理员 或者 本人
        if (!userFeignClient.isAdmin(request) && !oldQuestion.getUserId().equals(userId))
        {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean result = questionService.removeById(id);

        return R.success(result);
    }


    /**
     * 更新（仅管理员）
     *
     * @param questionUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public R<Boolean> updateQuestion(@RequestBody QuestionUpdateRequest questionUpdateRequest)
    {
        if (questionUpdateRequest == null || questionUpdateRequest.getId() <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionUpdateRequest, question);
        // 设置其他信息
        question.setTags(JSONUtil.toJsonStr(questionUpdateRequest.getTags()));
        question.setJudgeCase(JSONUtil.toJsonStr(questionUpdateRequest.getJudgeCase()));
        question.setJudgeConfig(JSONUtil.toJsonStr(questionUpdateRequest.getJudgeConfig()));

        long questionId = questionUpdateRequest.getId();

        // 判断是否存在
        Question oldQuestion = questionService.getById(questionId);
        ThrowUtils.throwIf(oldQuestion == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = questionService.updateById(question);
        return R.success(result);
    }

    /**
     * 根据 id 获取（未脱敏） 仅本人和管理员使用
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public R<Question> getQuestionById(long id)
    {
        if (id <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Question question = questionService.getById(id);
        if (question == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        User loginUser = userFeignClient.getLoginUser(request);
        // 不是 本人或管理员，则不能直接获取所有信息
        if (!question.getUserId().equals(loginUser.getId()) && !userFeignClient.isAdmin(loginUser))
        {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return R.success(question);
    }

    /**
     * 分页获取题目（未脱敏） 仅 管理员使用
     *
     * @param questionQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public R<Page<Question>> listQuestionByPage(@RequestBody QuestionQueryRequest questionQueryRequest)
    {
        if (questionQueryRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Page<Question> questionPage = new Page<>(questionQueryRequest.getCurrent(), questionQueryRequest.getPageSize());

        LambdaQueryWrapper<Question> queryWrapper = questionService.getQueryWrapper(questionQueryRequest);
        questionService.page(questionPage, queryWrapper);

        return R.success(questionPage);
    }


    /**
     * 分页获取当前用户创建的资源列表
     *
     * @param questionQueryRequest
     * @return
     */
    @PostMapping("/my/list/page/vo")
    public R<Page<QuestionVO>> listMyQuestionVOByPage(@RequestBody QuestionQueryRequest questionQueryRequest)
    {
        if (questionQueryRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userFeignClient.getLoginUser(request);
        questionQueryRequest.setUserId(loginUser.getId());
        // 分页
        long current = questionQueryRequest.getCurrent();
        long size = questionQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        Page<Question> questionPage = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionQueryRequest)
        );
        return R.success(questionService.getQuestionVOPage(questionPage));
    }


    /**
     * 编辑题目信息（用户）
     *
     * @param questionEditRequest
     * @return
     */
    @PostMapping("/edit")
    public R<Boolean> editQuestion(@RequestBody QuestionEditRequest questionEditRequest)
    {
        if (questionEditRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean isEdit = questionService.editQuestion(questionEditRequest);
        return R.success(isEdit);

    }

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @return 提交记录的 id
     */
    @PostMapping("/question_submit/do")
    public R<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest)
    {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest);
        return R.success(questionSubmitId);
    }


    /**
     * 分页获取 题目提交列表
     * （除管理员和用户本身外，其他人只能看到 非答案、非提交代码等 公开信息）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/question_submit/list/page")
    public R<Page<QuestionSubmitVO>> listQuestionSubmitByPage(
            @RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest
    )
    {
        if (questionSubmitQueryRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 分页查询
        int current = questionSubmitQueryRequest.getCurrent();
        int pageSize = questionSubmitQueryRequest.getPageSize();
        // 得到 题目提交列表的 原始数据
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, pageSize),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest)
        );

        // 根据用户信息，返回相应的 脱敏信息
        Page<QuestionSubmitVO> questionSubmitVOPage = questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage);

        return R.success(questionSubmitVOPage);

    }


    /**
     * 根据 id 获取提交记录（未脱敏） 仅本人和管理员使用
     *
     * @param id
     * @return
     */
    @GetMapping("/question_submit/get")
    public R<QuestionSubmit> getQuestionSubmitById(long id)
    {
        if (id <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        if (questionSubmit == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        User loginUser = userFeignClient.getLoginUser(request);
        // 不是 本人或管理员，则不能直接获取所有信息
        if (!questionSubmit.getUserId().equals(loginUser.getId()) && !userFeignClient.isAdmin(loginUser))
        {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return R.success(questionSubmit);
    }

    /**
     * 更新 提交记录（仅管理员）
     *
     * @param questionUpdateRequest
     * @return
     */
    @PostMapping("/question_submit/update")
    public R<Boolean> updateQuestion(@RequestBody QuestionSubmitUpdateRequest questionUpdateRequest)
    {
        if (questionUpdateRequest == null || questionUpdateRequest.getId() <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionUpdateRequest, questionSubmit);

        long questionId = questionUpdateRequest.getId();

        // 判断是否存在
        QuestionSubmit oldQuestionSubmit = questionSubmitService.getById(questionId);
        ThrowUtils.throwIf(oldQuestionSubmit == null, ErrorCode.NOT_FOUND_ERROR);

        boolean result = questionSubmitService.updateById(questionSubmit);
        return R.success(result);
    }

    @GetMapping("/get/question_submit/status")
    public R<Integer> queryQuestionSubmitStatus(@RequestParam("questionId") long questionId)
    {
        if (questionId <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionId);
        if (questionSubmit == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return R.success(questionSubmit.getStatus());

    }


    @PostMapping("/delete/question_submit")
    @AuthCheck
    public R<Boolean> deleteQuestionSubmitUsingPost(@RequestBody DeleteRequest deleteRequest)
    {
        if (deleteRequest == null)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean b = questionSubmitService.removeById(deleteRequest);
        return R.success(b);
    }

    @GetMapping("get/question/difficulty")
    @AuthCheck
    public R<Long> getQuestionDifficultyUsingGet(@RequestBody long difficulty)
    {
        if (difficulty <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return R.success(null);
    }


    @GetMapping("/get/question_submit")
    public R<QuestionSubmitVO> getQuestionSubmitByIdUsingGet(@RequestParam("id") long id)
    {
        if (id <= 0)
        {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);

        if (questionSubmit == null)
        {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return R.success(questionSubmitVO);

    }

    @GetMapping("get/question_submit/difficulty_number")
    public R<Long> getQuestionSubmitDifficultyUsingGet(@RequestParam("difficulty") long difficulty)
    {
        // if (id <= 0)
        // {
        //     throw new BusinessException(ErrorCode.PARAMS_ERROR);
        // }
        // QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        // QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        //
        // if (questionSubmit == null)
        // {
        //     throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        // }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        return R.success(1L);

    }


    @GetMapping("/get/question_submit/difficulty_pass_rate")
    public R<Double> getQuestionSubmitDifficultyPassRateUsingGet(@RequestParam("difficulty") long difficulty)
    {
        // if (id <= 0)
        // {
        //     throw new BusinessException(ErrorCode.PARAMS_ERROR);
        // }
        // QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        // QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        //
        // if (questionSubmit == null)
        // {
        //     throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        // }
        return R.success(0.5);

    }


    @GetMapping("/get/question_submit/pass_rate")
    public R<Double> getQuestionSubmitPassRateUsingGet()
    {
        // if (id <= 0)
        // {
        //     throw new BusinessException(ErrorCode.PARAMS_ERROR);
        // }
        // QuestionSubmit questionSubmit = questionSubmitService.getById(id);
        // QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        //
        // if (questionSubmit == null)
        // {
        //     throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        // }
        return R.success(0.5);

    }

}
