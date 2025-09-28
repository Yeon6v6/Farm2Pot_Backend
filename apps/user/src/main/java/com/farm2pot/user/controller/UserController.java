package com.farm2pot.user.controller;

import com.farm2pot.user.client.CoreServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final CoreServiceClient coreServiceClient;

    @GetMapping("/health")
    public Map<String, Object> health() {
        boolean coreHealthy = coreServiceClient.isHealthy();

        return Map.of(
                "status", "UP",
                "service", "user-service",
                "coreServiceHealthy", coreHealthy,
                "timestamp", System.currentTimeMillis()
        );
    }

    @PostMapping("/validate")
    public Map<String, Object> validateUserData(@RequestBody Map<String, Object> userData) {
        log.info("User data validation request: {}", userData);

        // Core 서비스를 통한 검증
        Map<String, Object> coreValidation = coreServiceClient.validateData(userData);

        // User 서비스 특화 검증 로직
        boolean userSpecificValid = validateUserSpecificFields(userData);

        boolean finalValid = (Boolean) coreValidation.get("valid") && userSpecificValid;

        return Map.of(
                "valid", finalValid,
                "coreValidation", coreValidation,
                "userValidation", userSpecificValid,
                "processedBy", "user-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @PostMapping("/process")
    public Map<String, Object> processUserData(@RequestBody Map<String, Object> request) {
        log.info("User data processing request: {}", request);

        String operation = (String) request.get("operation");
        Object data = request.get("data");

        // Core 서비스를 통한 공통 처리
        Map<String, Object> coreResult = coreServiceClient.processData(operation, data);

        // User 서비스 특화 처리
        Map<String, Object> userResult = processUserSpecificLogic(operation, data);

        return Map.of(
                "coreResult", coreResult,
                "userResult", userResult,
                "processedBy", "user-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @GetMapping("/config/{key}")
    public Map<String, Object> getUserConfig(@PathVariable String key) {
        log.info("User config request for key: {}", key);

        // Core 서비스에서 공통 설정 조회
        String coreConfig = coreServiceClient.getConfigValue(key);

        // User 서비스 특화 설정
        String userConfig = getUserSpecificConfig(key);

        return Map.of(
                "key", key,
                "coreConfig", coreConfig,
                "userConfig", userConfig,
                "retrievedAt", System.currentTimeMillis()
        );
    }

    @PostMapping("/users")
    public Map<String, Object> createUser(@RequestBody Map<String, Object> userRequest) {
        log.info("Create user request: {}", userRequest);

        // 입력 데이터 검증 (Core 서비스 활용)
        Map<String, Object> validation = coreServiceClient.validateData(userRequest);

        if (!(Boolean) validation.get("valid")) {
            return Map.of(
                    "success", false,
                    "message", "Invalid user data",
                    "validation", validation
            );
        }

        // 데이터 변환 (Core 서비스 활용)
        Map<String, Object> transformedData = coreServiceClient.transformData(userRequest);

        // 사용자 생성 로직 (User 서비스 특화)
        return Map.of(
                "success", true,
                "message", "User created successfully",
                "userId", "user_" + System.currentTimeMillis(),
                "transformedData", transformedData,
                "createdAt", System.currentTimeMillis()
        );
    }

    private boolean validateUserSpecificFields(Map<String, Object> userData) {
        // User 서비스만의 검증 로직
        if (!userData.containsKey("email") || !userData.containsKey("username")) {
            return false;
        }

        String email = (String) userData.get("email");
        return email != null && email.contains("@");
    }

    private Map<String, Object> processUserSpecificLogic(String operation, Object data) {
        // User 서비스 특화 로직
        return Map.of(
                "operation", operation,
                "userProcessed", true,
                "message", "User-specific processing completed for: " + operation
        );
    }

    private String getUserSpecificConfig(String key) {
        // User 서비스 특화 설정
        return switch (key) {
            case "user-session-timeout" -> "1800";
            case "max-login-attempts" -> "5";
            default -> null;
        };
    }
}