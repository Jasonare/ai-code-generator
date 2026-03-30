package com.ryan.aicodegenerator.common;

import java.io.Serializable;

import lombok.Data;

/**
 * DeleteRequest
 *
 * @author xuyh51035
 * @date 2026-03-30 15:53
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}

