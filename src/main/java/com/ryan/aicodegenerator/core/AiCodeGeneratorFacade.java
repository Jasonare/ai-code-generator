package com.ryan.aicodegenerator.core;

/**
 * AiCodeGeneratorFacade
 *
 * @author xuyh51035
 * @date 2026-04-01 14:52
 */

import java.io.File;

import org.springframework.stereotype.Service;

import com.ryan.aicodegenerator.ai.AiCodeGeneratorService;
import com.ryan.aicodegenerator.ai.model.HtmlCodeResult;
import com.ryan.aicodegenerator.ai.model.MultiFileCodeResult;
import com.ryan.aicodegenerator.enums.CodeGenTypeEnum;
import com.ryan.aicodegenerator.exception.BizException;
import com.ryan.aicodegenerator.exception.ErrorCode;

import jakarta.annotation.Resource;

/**
 * AI 代码生成外观类，组合生成和保存功能
 *
 * @author xuyh51035
 */
@Service
public class AiCodeGeneratorFacade {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    /**
     * 统一入口：根据类型生成并保存代码
     *
     * @param userMessage     用户提示词
     * @param codeGenTypeEnum 生成类型
     * @return 保存的目录
     */
    public File generateAndSaveCode(String userMessage, CodeGenTypeEnum codeGenTypeEnum) {
        if (codeGenTypeEnum == null) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, "生成类型为空");
        }
        return switch (codeGenTypeEnum) {
            case HTML -> generateAndSaveHtmlCode(userMessage);
            case MULTI_FILE -> generateAndSaveMultiFileCode(userMessage);
            default -> {
                String errorMessage = "不支持的生成类型：" + codeGenTypeEnum.getValue();
                throw new BizException(ErrorCode.SYSTEM_ERROR, errorMessage);
            }
        };
    }

    /**
     * 生成 HTML 模式的代码并保存
     *
     * @param userMessage 用户提示词
     * @return 保存的目录
     */
    private File generateAndSaveHtmlCode(String userMessage) {
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(userMessage);
        return CodeFileSaver.saveHtmlCodeResult(result);
    }

    /**
     * 生成多文件模式的代码并保存
     *
     * @param userMessage 用户提示词
     * @return 保存的目录
     */
    private File generateAndSaveMultiFileCode(String userMessage) {
        MultiFileCodeResult result = aiCodeGeneratorService.generateMultiFileCode(userMessage);
        return CodeFileSaver.saveMultiFileCodeResult(result);
    }
}
