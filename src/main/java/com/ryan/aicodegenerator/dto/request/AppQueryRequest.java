package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import com.ryan.aicodegenerator.common.BasePageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * AppQueryRequest
 *
 * @author xuyh51035
 * @date 2026-04-03 11:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppQueryRequest extends BasePageRequest implements Serializable {

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
     * 应用初始化的 prompt
     */
    private String initPrompt;

    /**
     * 代码生成类型（枚举）
     */
    private String codeGenType;

    /**
     * 部署标识
     */
    private String deployKey;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 创建用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
