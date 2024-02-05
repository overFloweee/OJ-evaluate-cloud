package com.hjw.model.dto.sandbox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse
{
    /**
     * 沙箱 执行程序 的输出结果
     */
    private List<String> outputList;

    /**
     * 沙箱的 接口 信息（超时等）
     */
    private String message;

    /**
     * 程序执行状态
     */
    private Integer status;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
