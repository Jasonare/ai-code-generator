package com.ryan.aicodegenerator.core;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryan.aicodegenerator.enums.CodeGenTypeEnum;

import jakarta.annotation.Resource;
import reactor.core.publisher.Flux;

/**
 * AiCodeGeneratorFacadeTest
 *
 * @author xuyh51035
 * @date 2026-04-01 17:14
 */
@SpringBootTest
class AiCodeGeneratorFacadeTest {

    @Resource
    private AiCodeGeneratorFacade aiCodeGeneratorFacade;

    @Test
    void generateHtmlCode() {
        aiCodeGeneratorFacade.generateAndSaveCode("做一个博客网站，不超过50行", CodeGenTypeEnum.HTML);
    }

    @Test
    void generateMultiFileCode() {
        aiCodeGeneratorFacade.generateAndSaveCode("做一个博客网站，不超过50行", CodeGenTypeEnum.MULTI_FILE);
    }

    @Test
    void generateAndSaveCodeStream() {
        Flux<String> codeStream = aiCodeGeneratorFacade.generateAndSaveCodeStream("做一个博客网站，不超过50行", CodeGenTypeEnum.HTML);
        // 阻塞等待所有数据收集完成
        List<String> result = codeStream.collectList().block();
        // 验证结果
        Assertions.assertNotNull(result);
        String completeContent = String.join("", result);
        Assertions.assertNotNull(completeContent);
    }
}