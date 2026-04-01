package com.ryan.aicodegenerator.ai;

import com.ryan.aicodegenerator.ai.model.HtmlCodeResult;
import com.ryan.aicodegenerator.ai.model.MultiFileCodeResult;

import dev.langchain4j.service.SystemMessage;

/**
 * AiCodeGeneratorService
 *
 * @author xuyh51035
 * @date 2026-04-01 11:16
 */
public interface AiCodeGeneratorService {

    /**
     * 生成 HTML 代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMessage);

    /**
     * 生成多文件代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);
}

