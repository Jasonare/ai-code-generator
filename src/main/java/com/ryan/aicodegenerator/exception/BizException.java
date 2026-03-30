package com.ryan.aicodegenerator.exception;

import lombok.Getter;

/**
 * BizException
 *
 * @author xuyh51035
 * @date 2026-03-30 15:46
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BizException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
