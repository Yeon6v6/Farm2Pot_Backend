package com.farm2pot.admin.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoreServiceClient {

    private final RestTemplate restTemplate;

    @Value("${farm2pot.core-service.url:http://localhost:8080}")
    private String coreServiceUrl;

    public Map<String, Object> validateData(Map<String, Object> data) {
        try {
            String url = coreServiceUrl + "/api/core/validate";
            Map<String, Object> response = restTemplate.postForObject(url, data, Map.class);
            log.info("Data validation completed via core service: {}", url);
            return response;
        } catch (Exception e) {
            log.error("Failed to validate data via core service", e);
            return Map.of(
                    "valid", false,
                    "message", "Core service unavailable",
                    "processedAt", System.currentTimeMillis()
            );
        }
    }

    public Map<String, Object> transformData(Map<String, Object> data) {
        try {
            String url = coreServiceUrl + "/api/core/transform";
            Map<String, Object> response = restTemplate.postForObject(url, data, Map.class);
            log.info("Data transformation completed via core service: {}", url);
            return response;
        } catch (Exception e) {
            log.error("Failed to transform data via core service", e);
            return Map.of(
                    "originalData", data,
                    "transformedAt", System.currentTimeMillis(),
                    "error", "Core service unavailable"
            );
        }
    }

    public String getConfigValue(String key) {
        try {
            String url = coreServiceUrl + "/api/core/config/" + key;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            log.info("Config value retrieved from core service: {} = {}", key, response.get("value"));
            return (String) response.get("value");
        } catch (Exception e) {
            log.error("Failed to get config value from core service", e);
            return "default";
        }
    }

    public Map<String, Object> processData(String operation, Object data) {
        try {
            String url = coreServiceUrl + "/api/core/process";
            Map<String, Object> request = Map.of(
                    "operation", operation,
                    "data", data
            );
            Map<String, Object> response = restTemplate.postForObject(url, request, Map.class);
            log.info("Data processing completed via core service: {}", operation);
            return response;
        } catch (Exception e) {
            log.error("Failed to process data via core service", e);
            return Map.of(
                    "error", "Core service unavailable",
                    "operation", operation,
                    "processedAt", System.currentTimeMillis()
            );
        }
    }

    public boolean isHealthy() {
        try {
            String url = coreServiceUrl + "/api/core/health";
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            return "UP".equals(response.get("status"));
        } catch (Exception e) {
            log.warn("Core service health check failed", e);
            return false;
        }
    }
}