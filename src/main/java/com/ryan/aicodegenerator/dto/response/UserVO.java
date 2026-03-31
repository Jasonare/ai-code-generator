package com.ryan.aicodegenerator.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

/**
 * UserVO
 *
 * @author xuyh51035
 * @date 2026-03-31 17:14
 */
@Data
public class UserVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin
     */
    private String userRole;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;
}

