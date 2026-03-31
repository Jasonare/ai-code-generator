package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * UserRegisterRequest
 *
 * @author xuyh51035
 * @date 2026-03-31 14:49
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;
}

