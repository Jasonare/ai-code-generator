package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * UserAddRequest
 *
 * @author xuyh51035
 * @date 2026-03-31 17:13
 */
@Data
public class UserAddRequest implements Serializable {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}

