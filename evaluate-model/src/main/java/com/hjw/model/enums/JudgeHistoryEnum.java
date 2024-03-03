package com.hjw.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户判题历史
 */
public enum JudgeHistoryEnum
{

    ACCEPTED("成功", 3L),
    HAVE_TRIED("尝试过", 0L),
    NO_TRIED("未尝试过", -1L);

    private final String text;

    private final long value;

    JudgeHistoryEnum(String text, long value)
    {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Long> getValues()
    {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeHistoryEnum getEnumByValue(Long value)
    {
        if (ObjectUtils.isEmpty(value))
        {
            return null;
        }
        for (JudgeHistoryEnum anEnum : JudgeHistoryEnum.values())
        {
            if (anEnum.value == value)
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

    public Long getValue()
    {
        return value;
    }
}
