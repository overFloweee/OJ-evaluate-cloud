package com.hjw.model.dto.questionsubmit;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionSubmitUpdateRequest implements Serializable {
    private static final long serialVersionUID = 655665399165114261L;
    /**
     * id
     */
    private Long id;

    /**
     * 判题信息（json 数组）
     * 对应着 JudgeInfo 实体类，其中包含 运行时间，消耗内存 -> InfoEnum -> String judgeInfo;
     */
    private String judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

}
