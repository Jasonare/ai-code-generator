package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * AppAdminUpdateRequest
 *
 * @author xuyh51035
 * @date 2026-04-03 11:29
 */
@Data
public class AppAdminUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用封面
     */
    private String cover;

    /**
     * 优先级
     */
    private Integer priority;

    private static final long serialVersionUID = 1L;
}
