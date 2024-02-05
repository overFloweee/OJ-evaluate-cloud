package com.hjw.judge.sandbox;


import com.hjw.judge.sandbox.impl.ExampleCodeSandbox;
import com.hjw.judge.sandbox.impl.RemoteCodeSandbox;
import com.hjw.judge.sandbox.impl.ThirdPartyCodeSandbox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 代码沙箱工厂（根据字符串参数，创建指定的代码沙箱实例）
 * 根据用户传入的参数，自动生成对应 实现类的代码沙箱
 */
@Component
@RequiredArgsConstructor
public class CodeSandboxFactory
{
    private final ExampleCodeSandbox exampleCodeSandbox;
    private final RemoteCodeSandbox remoteCodeSandbox;
    private final ThirdPartyCodeSandbox thirdPartyCodeSandbox;

    public static CodeSandbox newInstance()
    {
        return new ExampleCodeSandbox();
    }

    public CodeSandbox newInstance(String type)
    {

        switch (type)
        {
            case "example":
                return exampleCodeSandbox;
            case "remote":
                return remoteCodeSandbox;
            case "thirdParty":
                return thirdPartyCodeSandbox;
            default:
                return exampleCodeSandbox;
        }

    }

}

