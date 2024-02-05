package com.hjw.judge.strategy;


import com.hjw.model.dto.sandbox.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy
{
    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
