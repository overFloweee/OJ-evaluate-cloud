package com.hjw.question.controller.inner;


import com.hjw.model.dto.questionsubmit.QuestionSubmitUpdateRequest;
import com.hjw.model.entity.Question;
import com.hjw.model.entity.QuestionSubmit;
import com.hjw.question.service.QuestionService;
import com.hjw.question.service.QuestionSubmitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inner")
@RequiredArgsConstructor
public class InnerQuestionController
{

    private final QuestionService questionService;
    private final QuestionSubmitService questionSubmitService;


    @GetMapping("/get")
    public Question getQuestionById(@RequestParam("id") long questionId)
    {
        return questionService.getById(questionId);
    }


    @GetMapping("/question_submit/get")
    public QuestionSubmit getQuestionSubmitById(@RequestParam("id") long questionSubmitId)
    {
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update")
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit)
    {
        return questionSubmitService.updateById(questionSubmit);
    }

    @PostMapping("/question/update")
    public boolean updateQuestionById(@RequestBody Question question)
    {
        return questionService.updateById(question);
    }

}
