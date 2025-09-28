package com.farm2pot.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CoreService {

    public boolean validateData(Map<String, Object> data) {
        if (data == null || data.isEmpty()) {
            return false;
        }

        // 공통 검증 규칙들
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            if (entry.getValue() == null) {
                log.warn("Null value found for key: {}", entry.getKey());
                return false;
            }
        }

        return true;
    }

    public Map<String, Object> processData(String operation, Object data) {
        log.info("Processing operation: {} with data: {}", operation, data);

        // 공통 처리 로직
        switch (operation) {
            case "format":
                return formatData(data);
            case "validate":
                return Map.of("valid", validateData((Map<String, Object>) data));
            case "sanitize":
                return sanitizeData(data);
            default:
                return Map.of("error", "Unknown operation: " + operation);
        }
    }

    private Map<String, Object> formatData(Object data) {
        return Map.of(
                "formatted", true,
                "data", data,
                "timestamp", System.currentTimeMillis()
        );
    }

    private Map<String, Object> sanitizeData(Object data) {
        return Map.of(
                "sanitized", true,
                "data", data,
                "timestamp", System.currentTimeMillis()
        );
    }

    public String getConfigValue(String key) {
        // 설정값 조회 로직
        return switch (key) {
            case "max-file-size" -> "10MB";
            case "timeout" -> "30000";
            case "retry-count" -> "3";
            case "api-version" -> "v1";
            default -> null;
        };
    }
}