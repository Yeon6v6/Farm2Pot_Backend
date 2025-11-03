package com.farm2pot.common.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.Instant;

/**
 * packageName    : com.farm2pot.common.response
 * author         : TAEJIN
 * date           : 2025-10-06
 * description    : 에러 Response
 */
@Builder
public record ErrorResponse(
        int status,
        String error,
        String message,
        String timestamp,
        String path
) {

    // HttpStatus 기반
    public static ErrorResponse of(HttpStatus status, String message, String path) {
        return new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                message,
                Instant.now().toString(),
                path
        );
    }

    // int 코드 기반
    public static ErrorResponse of(int status, String message, String path) {
        return new ErrorResponse(
                status,
                HttpStatus.valueOf(status).getReasonPhrase(),
                message,
                Instant.now().toString(),
                path
        );
    }
}
