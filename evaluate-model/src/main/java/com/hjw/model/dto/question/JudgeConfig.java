package com.hjw.model.dto.question;


import lombok.Data;

/**
 * 判题配置
 */
@Data
public class JudgeConfig
{



    /**
     * 运行时间限制(ms)
     */
    private Long timeLimit;

    /**
     * 运行内存限制(kb)
     */
    private Long memoryLimit;


    /**
     * 堆栈限制限制(kb)
     */
    private Long stackLimit;

}
