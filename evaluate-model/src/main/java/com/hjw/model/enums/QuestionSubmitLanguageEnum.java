package com.hjw.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 提交编程语言枚举
 */
public enum QuestionSubmitLanguageEnum
{

    JAVA("java", "java"),
    CPLUSPLUS("c++", "c++"),
    GOLANG("golang", "golang"),
    JAVASCRIPT("javascript", "javascript"),
    PYTHON("python", "python");

    private final String text;

    private final String value;

    QuestionSubmitLanguageEnum(String text, String value)
    {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues()
    {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitLanguageEnum getEnumByValue(String value)
    {
        if (ObjectUtils.isEmpty(value))
        {
            return null;
        }
        for (QuestionSubmitLanguageEnum anEnum : QuestionSubmitLanguageEnum.values())
        {
            if (anEnum.value.equals(value))
            {
                return anEnum;
            }
        }
        return null;
    }

    public String getText()
    {
        return text;
    }

    public String getValue()
    {
        return value;
    }
}
