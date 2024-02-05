package com.hjw.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目提交 实体类
 * @TableName question_submit
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目id
     */
    private Long questionId;

    private static final long serialVersionUID = 1L;
}
