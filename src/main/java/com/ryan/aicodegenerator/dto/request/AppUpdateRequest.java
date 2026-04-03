package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * AppUpdateRequest
 *
 * @author xuyh51035
 * @date 2026-04-03 11:10
 */
@Data
public class AppUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    private static final long serialVersionUID = 1L;
}
