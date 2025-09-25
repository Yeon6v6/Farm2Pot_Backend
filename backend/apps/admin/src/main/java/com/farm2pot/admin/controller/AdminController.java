package com.farm2pot.admin.controller;

import com.farm2pot.admin.client.CoreServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final CoreServiceClient coreServiceClient;

    @GetMapping("/health")
    public Map<String, Object> health() {
        boolean coreHealthy = coreServiceClient.isHealthy();

        return Map.of(
                "status", "UP",
                "service", "admin-service",
                "coreServiceHealthy", coreHealthy,
                "timestamp", System.currentTimeMillis()
        );
    }

    @PostMapping("/validate")
    public Map<String, Object> validateAdminData(@RequestBody Map<String, Object> adminData) {
        log.info("Admin data validation request: {}", adminData);

        // Core 서비스를 통한 검증
        Map<String, Object> coreValidation = coreServiceClient.validateData(adminData);

        // Admin 서비스 특화 검증 로직
        boolean adminSpecificValid = validateAdminSpecificFields(adminData);

        boolean finalValid = (Boolean) coreValidation.get("valid") && adminSpecificValid;

        return Map.of(
                "valid", finalValid,
                "coreValidation", coreValidation,
                "adminValidation", adminSpecificValid,
                "processedBy", "admin-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @PostMapping("/process")
    public Map<String, Object> processAdminData(@RequestBody Map<String, Object> request) {
        log.info("Admin data processing request: {}", request);

        String operation = (String) request.get("operation");
        Object data = request.get("data");

        // Core 서비스를 통한 공통 처리
        Map<String, Object> coreResult = coreServiceClient.processData(operation, data);

        // Admin 서비스 특화 처리
        Map<String, Object> adminResult = processAdminSpecificLogic(operation, data);

        return Map.of(
                "coreResult", coreResult,
                "adminResult", adminResult,
                "processedBy", "admin-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @GetMapping("/config/{key}")
    public Map<String, Object> getAdminConfig(@PathVariable String key) {
        log.info("Admin config request for key: {}", key);

        // Core 서비스에서 공통 설정 조회
        String coreConfig = coreServiceClient.getConfigValue(key);

        // Admin 서비스 특화 설정
        String adminConfig = getAdminSpecificConfig(key);

        return Map.of(
                "key", key,
                "coreConfig", coreConfig,
                "adminConfig", adminConfig,
                "retrievedAt", System.currentTimeMillis()
        );
    }

    @PostMapping("/users/manage")
    public Map<String, Object> manageUser(@RequestBody Map<String, Object> userRequest) {
        log.info("Admin user management request: {}", userRequest);

        // 입력 데이터 검증 (Core 서비스 활용)
        Map<String, Object> validation = coreServiceClient.validateData(userRequest);

        if (!(Boolean) validation.get("valid")) {
            return Map.of(
                    "success", false,
                    "message", "Invalid user management data",
                    "validation", validation
            );
        }

        // 관리자 권한 검증 (Admin 서비스 특화)
        if (!hasAdminPermission(userRequest)) {
            return Map.of(
                    "success", false,
                    "message", "Insufficient admin permissions"
            );
        }

        // 데이터 변환 (Core 서비스 활용)
        Map<String, Object> transformedData = coreServiceClient.transformData(userRequest);

        // 사용자 관리 로직 (Admin 서비스 특화)
        return Map.of(
                "success", true,
                "message", "User management operation completed",
                "operationId", "admin_op_" + System.currentTimeMillis(),
                "transformedData", transformedData,
                "processedAt", System.currentTimeMillis()
        );
    }

    @GetMapping("/dashboard/stats")
    public Map<String, Object> getDashboardStats() {
        log.info("Admin dashboard stats request");

        // Core 서비스를 통한 시스템 상태 확인
        boolean coreHealthy = coreServiceClient.isHealthy();

        // Admin 서비스 특화 통계 생성
        return Map.of(
                "coreServiceStatus", coreHealthy ? "UP" : "DOWN",
                "totalUsers", 1250,
                "activeUsers", 342,
                "systemLoad", "65%",
                "generatedAt", System.currentTimeMillis()
        );
    }

    private boolean validateAdminSpecificFields(Map<String, Object> adminData) {
        // Admin 서비스만의 검증 로직
        if (!adminData.containsKey("adminLevel") || !adminData.containsKey("permission")) {
            return false;
        }

        String adminLevel = (String) adminData.get("adminLevel");
        return adminLevel != null && !adminLevel.isEmpty();
    }

    private Map<String, Object> processAdminSpecificLogic(String operation, Object data) {
        // Admin 서비스 특화 로직
        return Map.of(
                "operation", operation,
                "adminProcessed", true,
                "message", "Admin-specific processing completed for: " + operation,
                "requiresAudit", true
        );
    }

    private String getAdminSpecificConfig(String key) {
        // Admin 서비스 특화 설정
        return switch (key) {
            case "admin-session-timeout" -> "7200";
            case "max-admin-sessions" -> "3";
            case "audit-retention-days" -> "365";
            default -> null;
        };
    }

    private boolean hasAdminPermission(Map<String, Object> request) {
        // 관리자 권한 검증 로직
        String adminLevel = (String) request.get("adminLevel");
        return "super_admin".equals(adminLevel) || "admin".equals(adminLevel);
    }
}