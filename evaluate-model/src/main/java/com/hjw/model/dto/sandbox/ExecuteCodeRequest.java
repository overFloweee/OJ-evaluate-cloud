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
public class ExecuteCodeRequest
{

    /**
     * 输入用例
     */
    private List<String> inputList;

    /**
     * 提交的代码
     */
    private String code;

    /**
     * 与编程语言
     */
    private String language;
}
