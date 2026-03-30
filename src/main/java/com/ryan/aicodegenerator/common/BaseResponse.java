package com.ryan.aicodegenerator.common;

import java.io.Serializable;

import com.ryan.aicodegenerator.exception.ErrorCode;

import lombok.Data;

/**
 * BaseResponse
 *
 * @author xuyh51035
 * @date 2026-03-30 15:49
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

