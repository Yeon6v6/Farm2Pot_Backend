package com.farm2pot.common.response;

import lombok.Builder;

/**
 * packageName    : com.farm2pot.common.response
 * author         : TAEJIN
 * date           : 2025-10-06
 * description    : Response용 Wrapper 클래스
 */
@Builder
public record ResponseMessage<T>(
        boolean success,
        String message,
        T data
) {

    // 성공 + 데이터
    public static <T> ResponseMessage<T> success(T data) {
        return ResponseMessage.<T>builder()
                .success(true)
                .message("success")
                .data(data)
                .build();
    }

    // 성공 + 메세지
    public static <T> ResponseMessage<T> success(String message) {
        return ResponseMessage.<T>builder()
                .success(true)
                .message(message)
                .data(null)
                .build();
    }

    // 성공 + 메시지 + 데이터
    public static <T> ResponseMessage<T> success(String message, T data) {
        return ResponseMessage.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    // 실패 + 메시지
    public static <T> ResponseMessage<T> fail(String message) {
        return ResponseMessage.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .build();
    }

    // 실패 + 데이터
    public static <T> ResponseMessage<T> fail(T data) {
        return ResponseMessage.<T>builder()
                .success(false)
                .message("fail")
                .data(data)
                .build();
    }

    // 실패 + 메세지 + 데이터
    public static <T> ResponseMessage<T> fail(String message, T data ) {
        return ResponseMessage.<T>builder()
                .success(false)
                .message(message)
                .data(data)
                .build();
    }
}