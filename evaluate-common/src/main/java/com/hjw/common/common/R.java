package com.hjw.common.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable
{
    private static final long serialVersionUID = 6682215287252208284L;

    private int code;
    private String message;
    private T data;

    public R(int code, String msg)
    {
        this.code = code;
        this.message = msg;
    }

    /**
     * 成功响应
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> success(T data)
    {
        return new R<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    /**
     * 失败响应
     *
     * @return
     */
    public static R error(int code, String msg)
    {
        return new R(code, msg);
    }

    public static R error(ErrorCode errorCode)
    {
        return new R(errorCode.getCode(), errorCode.getMessage());
    }

    public static R error(ErrorCode errorCode, String msg)
    {
        return new R(errorCode.getCode(), msg);
    }


}
