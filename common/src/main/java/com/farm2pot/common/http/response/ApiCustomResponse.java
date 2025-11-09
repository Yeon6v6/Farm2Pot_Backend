package com.farm2pot.common.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * 공통 API 성공 응답 형태
 * @param <T> 응답 데이터 타입
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiCustomResponse<T>(
        T data,
        String responseCode,
        String responseMessage
) {

    // 성공 응답 (데이터만)
    public static <T> ApiCustomResponse<T> success(T data) {
        return ApiCustomResponse.<T>builder()
                .data(data)
                .responseCode(ApiResponseCode.SUCCESS.getCode())
                .responseMessage(ApiResponseCode.SUCCESS.getMessage())
                .build();
    }

    // 성공 응답 (데이터 + 커스텀 메시지)
    public static <T> ApiCustomResponse<T> success(T data, String message) {
        return ApiCustomResponse.<T>builder()
                .data(data)
                .responseCode(ApiResponseCode.SUCCESS.getCode())
                .responseMessage(message)
                .build();
    }

    // 성공 응답 (ResponseCode 기반)
    public static <T> ApiCustomResponse<T> of(T data, ApiResponseCode responseCode) {
        return ApiCustomResponse.<T>builder()
                .data(data)
                .responseCode(responseCode.getCode())
                .responseMessage(responseCode.getMessage())
                .build();
    }

    // 성공 응답 (ResponseCode + 커스텀 메시지)
    public static <T> ApiCustomResponse<T> of(T data, ApiResponseCode responseCode, String message) {
        return ApiCustomResponse.<T>builder()
                .data(data)
                .responseCode(responseCode.getCode())
                .responseMessage(message)
                .build();
    }
}
