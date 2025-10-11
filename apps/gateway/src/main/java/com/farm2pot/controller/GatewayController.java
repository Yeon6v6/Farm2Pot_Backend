package com.farm2pot.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    private WebClient webClient;

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @GetMapping("/health")
    public Mono<Map<String, Object>> health() {
        return Mono.just(Map.of(
                "status", "UP",
                "service", "gateway-service",
                "timestamp", System.currentTimeMillis()
        ));
    }

    @RequestMapping("/api/core/**")
    public Mono<ResponseEntity<Object>> routeToCore(ServerHttpRequest request,
                                               @RequestBody(required = false) Mono<String> body) {
        return routeRequest(coreServiceUrl, request, body);
    }

    @RequestMapping("/api/user/**")
    public Mono<ResponseEntity<Object>> routeToUser(ServerHttpRequest request,
                                               @RequestBody(required = false) Mono<String> body) {
        return routeRequest(userServiceUrl, request, body);
    }

    @RequestMapping("/api/admin/**")
    public Mono<ResponseEntity<Object>> routeToAdmin(ServerHttpRequest request,
                                                @RequestBody(required = false) Mono<String> body) {
        return routeRequest(adminServiceUrl, request, body);
    }

    @RequestMapping("/api/subs/**")
    public Mono<ResponseEntity<Object>> routeToSubscription(ServerHttpRequest request,
                                                       @RequestBody(required = false) Mono<String> body) {
        return routeRequest(subscriptionServiceUrl, request, body);
    }

    private Mono<ResponseEntity<Object>> routeRequest(String targetUrl,
                                                      ServerHttpRequest request,
                                                      Mono<String> body) {
        try {
            String path = request.getURI().getPath();
            HttpMethod httpMethod = request.getMethod();
            if (httpMethod == null) {
                return Mono.just(ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid HTTP method")));
            }

            log.info("Routing {} {} -> {}", httpMethod.name(), path, targetUrl);

            // 1. 헤더 복사
            HttpHeaders headers = new HttpHeaders();
            request.getHeaders().forEach(headers::addAll);

            // 2. URL 조립
            String query = request.getURI().getQuery();
            String fullUrl = targetUrl + path + (query != null ? "?" + query : "");

            // 3. WebClient 요청
            WebClient.RequestBodySpec requestSpec = webClient.method(httpMethod)
                    .uri(fullUrl)
                    .headers(h -> h.addAll(headers));

            Mono<String> requestBody = body.defaultIfEmpty("");

            // 4. 모든 HTTP 메서드 처리
            return requestBody.flatMap(reqBody -> {
                WebClient.RequestHeadersSpec<?> spec;
                if (httpMethod == HttpMethod.GET || httpMethod == HttpMethod.DELETE) {
                    spec = requestSpec;
                } else {
                    spec = requestSpec.bodyValue(reqBody);
                }

                // 5. 응답 상태와 body 그대로 반환
                return spec.exchangeToMono(clientResponse ->
                        clientResponse.bodyToMono(Object.class)
                                .map(responseBody ->
                                        ResponseEntity.status(clientResponse.statusCode())
                                                .headers(clientResponse.headers().asHttpHeaders())
                                                .body(responseBody)
                                )
                );
            });

        } catch (Exception e) {
            log.error("Error routing request: ", e);
            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Gateway routing failed: " + e.getMessage())));
        }
    }
}