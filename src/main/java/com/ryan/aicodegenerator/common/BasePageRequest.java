package com.ryan.aicodegenerator.common;

import lombok.Data;

/**
 * BasePageRequest
 *
 * @author xuyh51035
 * @date 2026-03-30 15:52
 */
@Data
public class BasePageRequest {

    /**
     * 当前页号
     */
    private int pageNum = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认降序）
     */
    private String sortOrder = "descend";
}

