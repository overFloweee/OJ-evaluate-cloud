package com.hjw.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @TableName question
 */
@Data
public class QuestionEditRequest implements Serializable
{


    /**
     * id
     */
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 题目标签分类 集合
     */
    private List<String> tags;

    /**
     * 题目标准答案
     */
    private String answer;

    /**
     * 判题用例 集合
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置 集合
     */
    private List<JudgeCase> judgeConfig;

    private static final long serialVersionUID = 1L;
}
