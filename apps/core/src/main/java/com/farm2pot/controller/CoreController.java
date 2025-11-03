package com.farm2pot.controller;

import com.farm2pot.common.response.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/core")
public class CoreController {

    @GetMapping("/health")
    public ResponseMessage health() {
        return ResponseMessage.success(Map.of(
                "status", "UP",
                "service", "core-service",
                "timestamp", System.currentTimeMillis()
            )
        );
    }
}
