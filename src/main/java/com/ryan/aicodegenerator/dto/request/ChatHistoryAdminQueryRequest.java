package com.ryan.aicodegenerator.dto.request;

import com.ryan.aicodegenerator.common.BasePageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * ChatHistoryAdminQueryRequest
 *
 * @author xuyh51035
 * @date 2026-04-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ChatHistoryAdminQueryRequest extends BasePageRequest implements Serializable {

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 消息类型
     */
    private String messageType;

    /**
     * 关键词
     */
    private String keyword;

    private static final long serialVersionUID = 1L;
}
