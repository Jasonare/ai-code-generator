package com.ryan.aicodegenerator.ai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ryan.aicodegenerator.core.AiCodeGeneratorFacade;
import com.ryan.aicodegenerator.enums.CodeGenTypeEnum;

import jakarta.annotation.Resource;

/**
 * AiCodeGeneratorServiceTest
 *
 * @author xuyh51035
 * @date 2026-04-01 11:27
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
}