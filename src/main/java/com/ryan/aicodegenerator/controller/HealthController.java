package com.ryan.aicodegenerator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.aicodegenerator.common.BaseResponse;
import com.ryan.aicodegenerator.common.ResultUtils;

/**
 * HealthController
 *
 * @author xuyh51035
 * @date 2026-03-30 14:16
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/")
    public BaseResponse<?> healthCheck() {
        return ResultUtils.success("ok");
    }
}

