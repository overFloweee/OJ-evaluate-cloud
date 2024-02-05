package com.hjw.judge.sandbox;


import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;

/**
 * 代码沙箱，执行代码
 */
public interface CodeSandbox
{

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
