package com.ryan.aicodegenerator.dto.request;

import java.io.Serializable;

import lombok.Data;

/**
 * AppAddRequest
 *
 * @author xuyh51035
 * @date 2026-04-03 11:08
 */
@Data
public class AppAddRequest implements Serializable {

    /**
     * 应用初始化的 prompt
     */
    private String initPrompt;

    private static final long serialVersionUID = 1L;
}
