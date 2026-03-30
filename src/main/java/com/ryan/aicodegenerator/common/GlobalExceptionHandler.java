package com.ryan.aicodegenerator.common;

import io.swagger.v3.oas.annotations.Hidden;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ryan.aicodegenerator.exception.BizException;
import com.ryan.aicodegenerator.exception.ErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * GlobalExceptionHandler
 *
 * @author xuyh51035
 * @date 2026-03-30 15:50
 */
@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public BaseResponse<?> bizExceptionHandler(BizException e) {
        log.error("BizException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}

