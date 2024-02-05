package com.hjw.judge.strategy;

import com.hjw.judge.strategy.impl.DefaultJudgeStrategyImpl;
import com.hjw.judge.strategy.impl.JavaLanguageJudgeStrategyImpl;
import com.hjw.model.dto.sandbox.JudgeInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 判题管理
 * 判断使用哪些判题策略
 */
@Service
public class JudgeManage
{
    /**
     * 执行判题策略
     *
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext)
    {
        String language = judgeContext.getQuestionSubmit().getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();
        if ("java".equals(language))
        {
            judgeStrategy = new JavaLanguageJudgeStrategyImpl();
        }


        return judgeStrategy.doJudge(judgeContext);
    }
}
