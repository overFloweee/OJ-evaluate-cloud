package com.hjw.api.service;

import com.hjw.model.dto.questionsubmit.QuestionSubmitUpdateRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 86157
 * @description 针对表【question(题目)】的数据库操作Service
 * @createDate 2024-01-22 21:50:08
 */
@FeignClient(name = "evaluate-question-service",path = "/api/question/inner")
public interface QuestionFeignClient
{

    @GetMapping("/get")
    Question getQuestionById(@RequestParam("id") long questionId);


    @GetMapping("/question_submit/get")
    QuestionSubmit getQuestionSubmitById(@RequestParam("id") long questionSubmitId);

    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(QuestionSubmit updateQuestionSubmit);

    @PostMapping("/question/update")
    boolean updateQuestionById(Question updateQuestion);
}
