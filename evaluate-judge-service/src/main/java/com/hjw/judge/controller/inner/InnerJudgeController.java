package com.hjw.judge.controller.inner;

import com.hjw.api.service.JudgeClient;
import com.hjw.judge.service.JudgeService;
import com.hjw.model.entity.QuestionSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/inner")
public class InnerJudgeController
{

    @Resource
    private JudgeService judgeService;

    /**
     * 判题
     *
     * @param questionSubmitId
     * @return
     */
    @PostMapping("/do")
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId)
    {
        return judgeService.doJudge(questionSubmitId);
    }
}
