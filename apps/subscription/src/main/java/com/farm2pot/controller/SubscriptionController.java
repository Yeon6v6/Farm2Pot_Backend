package com.farm2pot.controller;

import com.farm2pot.common.response.ResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/subs")
@RequiredArgsConstructor
@Slf4j
public class SubscriptionController {

    @GetMapping("/health")
    public ResponseMessage health() {
        return ResponseMessage.success(Map.of(
                        "status", "UP",
                        "service", "subscription-service",
                        "timestamp", System.currentTimeMillis()
                )
        );
    }
}