package com.ryan.aicodegenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ryan.aicodegenerator.model.mapper")
public class AiCodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeGeneratorApplication.class, args);
    }

}
