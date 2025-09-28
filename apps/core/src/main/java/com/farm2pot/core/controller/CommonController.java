package com.farm2pot.core.controller;

import com.farm2pot.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/core")
@RequiredArgsConstructor
@Slf4j
public class CommonController {

    private final CoreService coreService;

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "core-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @PostMapping("/validate")
    public Map<String, Object> validate(@RequestBody Map<String, Object> data) {
        log.info("Validation request received: {}", data);

        boolean isValid = coreService.validateData(data);

        return Map.of(
                "valid", isValid,
                "message", isValid ? "Valid data" : "Invalid data",
                "processedAt", System.currentTimeMillis()
        );
    }

    @PostMapping("/transform")
    public Map<String, Object> transform(@RequestBody Map<String, Object> data) {
        log.info("Transform request received: {}", data);

        Map<String, Object> transformed = Map.of(
                "originalData", data,
                "transformedAt", System.currentTimeMillis(),
                "processedBy", "core-service"
        );

        return transformed;
    }

    @GetMapping("/config/{key}")
    public Map<String, Object> getConfig(@PathVariable String key) {
        log.info("Config request for key: {}", key);

        String value = coreService.getConfigValue(key);
        if (value == null) {
            value = "unknown";
        }

        return Map.of(
                "key", key,
                "value", value,
                "retrievedAt", System.currentTimeMillis()
        );
    }

    @PostMapping("/process")
    public Map<String, Object> process(@RequestBody Map<String, Object> request) {
        log.info("Process request received: {}", request);

        String operation = (String) request.get("operation");
        Object data = request.get("data");

        return coreService.processData(operation, data);
    }
}