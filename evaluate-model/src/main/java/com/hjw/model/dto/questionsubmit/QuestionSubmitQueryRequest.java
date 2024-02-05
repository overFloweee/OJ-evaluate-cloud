package com.hjw.model.dto.questionsubmit;

import com.hjw.common.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题目提交 查询 实体类
 * @TableName question_submit
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交状态
     */
    private Integer status;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
