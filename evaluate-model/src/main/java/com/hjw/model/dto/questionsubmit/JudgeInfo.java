package com.hjw.model.dto.questionsubmit;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 判题运行信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeInfo
{
    /**
     * 判题信息
     */
    private String message;

    /**
     * 程序消耗内存
     */
    private Long memory;

    /**
     * 程序运行时间
     */
    private Long time;
}
