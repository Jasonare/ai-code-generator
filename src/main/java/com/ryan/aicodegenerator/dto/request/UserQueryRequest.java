package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import com.ryan.aicodegenerator.common.BasePageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * UserQueryRequest
 *
 * @author xuyh51035
 * @date 2026-03-31 17:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends BasePageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 简介
     */
    private String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}

