package com.ryan.aicodegenerator.enums;

import cn.hutool.core.util.ObjUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MessageTypeEnum
 *
 * @author xuyh51035
 * @date 2026-04-14
 */
@Getter
@AllArgsConstructor
public enum MessageTypeEnum {

    USER("用户消息", "user"),
    AI("AI消息", "ai"),
    AI_ERROR("AI错误消息", "ai_error");

    private final String text;
    private final String value;

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值
     * @return 枚举
     */
    public static MessageTypeEnum getEnumByValue(String value) {
        if (ObjUtil.isEmpty(value)) {
            return null;
        }
        for (MessageTypeEnum anEnum : MessageTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
