package com.hjw.judge.sandbox.impl;


import com.hjw.judge.sandbox.CodeSandbox;
import com.hjw.model.dto.sandbox.ExecuteCodeRequest;
import com.hjw.model.dto.sandbox.ExecuteCodeResponse;
import com.hjw.model.dto.sandbox.JudgeInfo;
import com.hjw.model.enums.JudgeInfoEnum;
import com.hjw.model.enums.QuestionSubmitStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 示例代码沙箱
 */
@Component
public class ExampleCodeSandbox implements CodeSandbox
{

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest)
    {
        List<String> inputList = executeCodeRequest.getInputList();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(Arrays.asList("3 4","6 7"));
        executeCodeResponse.setMessage("示例代码沙箱执行");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoEnum.ACCEPTED.getValue());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
