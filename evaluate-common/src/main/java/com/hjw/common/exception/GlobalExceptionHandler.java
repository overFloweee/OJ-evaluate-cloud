package com.hjw.common.exception;

import com.hjw.common.common.ErrorCode;
import com.hjw.common.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 *
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public R<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return R.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
