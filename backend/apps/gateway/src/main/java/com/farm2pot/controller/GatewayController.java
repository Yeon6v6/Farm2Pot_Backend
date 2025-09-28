package com.farm2pot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class GatewayController {

    @Value("${routes.core.url:http://core-service:8080}")
    private String coreServiceUrl;

    @Value("${routes.user.url:http://user-service:8081}")
    private String userServiceUrl;

    @Value("${routes.admin.url:http://admin-service:8082}")
    private String adminServiceUrl;

    @Value("${routes.subscription.url:http://subscription-service:8083}")
    private String subscriptionServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "gateway-service",
                "timestamp", System.currentTimeMillis()
        );
    }

    @RequestMapping("/api/core/**")
    public ResponseEntity<?> routeToCore(HttpServletRequest request, @RequestBody(required = false) Object body) {
        return routeRequest(coreServiceUrl, request, body);
    }

    @RequestMapping("/api/user/**")
    public ResponseEntity<?> routeToUser(HttpServletRequest request, @RequestBody(required = false) Object body) {
        return routeRequest(userServiceUrl, request, body);
    }

    @RequestMapping("/api/admin/**")
    public ResponseEntity<?> routeToAdmin(HttpServletRequest request, @RequestBody(required = false) Object body) {
        return routeRequest(adminServiceUrl, request, body);
    }

    @RequestMapping("/api/subs/**")
    public ResponseEntity<?> routeToSubscription(HttpServletRequest request, @RequestBody(required = false) Object body) {
        return routeRequest(subscriptionServiceUrl, request, body);
    }

    private ResponseEntity<?> routeRequest(String targetUrl, HttpServletRequest request, Object body) {
        try {
            String path = request.getRequestURI();
            String method = request.getMethod();
            HttpMethod httpMethod = HttpMethod.valueOf(method);

            log.info("Routing {} {} to {}", method, path, targetUrl);

            // 1. 모든 헤더 복사 (Header, Authorization, Cookie 등)
            HttpHeaders headers = new HttpHeaders();
            request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
                // Content-Length와 Host 헤더는 RestTemplate이 자체 처리하므로 제외합니다.
                if (!"content-length".equalsIgnoreCase(headerName) && !"host".equalsIgnoreCase(headerName)) {
                    request.getHeaders(headerName).asIterator().forEachRemaining(headerValue -> {
                        headers.add(headerName, headerValue);
                    });
                }
            });

            // 2. Content-Type 처리
            String contentType = request.getContentType();
            // 원본 Content-Type이 있다면 그대로 복사
            if (contentType != null) {
                headers.set(HttpHeaders.CONTENT_TYPE, contentType);
            } else if (body != null && httpMethod != HttpMethod.GET && httpMethod != HttpMethod.HEAD && httpMethod != HttpMethod.OPTIONS) {
                // Body가 있고, Body를 가질 수 있는 Method인데 Content-Type이 없다면 기본 JSON으로 설정
                headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            }

            // 3. HTTP Method에 따른 Entity (Body) 구성
            HttpEntity<?> entity;
            // GET/HEAD/OPTIONS 요청은 Body가 없으므로 Headers만 담아 전달
            if (httpMethod == HttpMethod.GET || httpMethod == HttpMethod.HEAD || httpMethod == HttpMethod.OPTIONS) {
                entity = new HttpEntity<>(headers);
            }
            // POST, PUT, DELETE, PATCH 등: Body와 Headers를 함께 전달
            else {
                // @RequestBody(required = false) Object body로 받은 본문을 그대로 사용합니다. (null일 수도 있음)
                entity = new HttpEntity<>(body, headers);
            }

            // 4. URL 구성: Path Variable 및 Query Parameter 처리
            String queryString = request.getQueryString();
            String fullUrl = targetUrl + path + (queryString != null ? "?" + queryString : "");

            // 5. 요청 전달
            return restTemplate.exchange(
                    fullUrl,
                    httpMethod,
                    entity,
                    Object.class
            );
        } catch (Exception e) {
            log.error("Error routing request: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Gateway routing failed: " + e.getMessage()));
        }
    }
}