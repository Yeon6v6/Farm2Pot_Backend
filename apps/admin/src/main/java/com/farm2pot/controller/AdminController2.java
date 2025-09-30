package com.farm2pot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * packageName    : com.farm2pot.controller
 * fileName       : UserController2
 * author         : Administrator
 * date           : 2025-09-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-09-30        Administrator       최초 생성
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController2 {
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "admin-service",
                "timestamp", System.currentTimeMillis()
        );
    }
}
