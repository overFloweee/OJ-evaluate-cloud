package com.hjw.question.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.model.dto.question.QuestionAddRequest;
import com.hjw.model.dto.question.QuestionEditRequest;
import com.hjw.model.dto.question.QuestionQueryRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.vo.QuestionVO;

/**
 * @author 86157
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2024-01-22 21:50:08
 */
public interface QuestionService extends IService<Question>
{

    /**
     * 校验题目信息是否合法
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);


    /**
     * 获取查询包装类（用户根据哪里字段查询，根据前端传递的参数，生成mybatis支持的查询类）
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);


    /**
     * 返回题目的VO对象(转换成安全对象)
     */
    QuestionVO getQuestionVO(Question question);


    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage);


    /**
     * 新增题目
     * @param questionAddRequest
     * @return
     */
    Long addQuestion(QuestionAddRequest questionAddRequest);

    boolean editQuestion(QuestionEditRequest questionEditRequest);
}
