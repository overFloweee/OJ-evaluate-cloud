package com.hjw.judge.service;


import com.hjw.model.entity.QuestionSubmit;

public interface JudgeService
{
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
