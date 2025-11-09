package com.farm2pot.common.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * 공통 API 에러 응답 형태
 */
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(
        Object data,
        String responseCode,
        String responseMessage
) {

    // 에러 응답 (ResponseCode 기반)
    public static ApiErrorResponse of(ApiResponseCode responseCode) {
        return ApiErrorResponse.builder()
                .data(null)
                .responseCode(responseCode.getCode())
                .responseMessage(responseCode.getMessage())
                .build();
    }

    // 에러 응답 (ResponseCode + 메시지)
    public static ApiErrorResponse of(ApiResponseCode responseCode, String message) {
        return ApiErrorResponse.builder()
                .data(null)
                .responseCode(responseCode.getCode())
                .responseMessage(message)
                .build();
    }

    // 에러 응답 (ResponseCode + 데이터 + 메시지)
    public static ApiErrorResponse of(ApiResponseCode responseCode, String message, Object data) {
        return ApiErrorResponse.builder()
                .data(data)
                .responseCode(responseCode.getCode())
                .responseMessage(message)
                .build();
    }

    // 에러 응답 (코드 + 메시지 직접 지정)
    public static ApiErrorResponse of(String code, String message) {
        return ApiErrorResponse.builder()
                .data(null)
                .responseCode(code)
                .responseMessage(message)
                .build();
    }

    // 에러 응답 (코드 + 메시지 + 데이터)
    public static ApiErrorResponse of(String code, String message, Object data) {
        return ApiErrorResponse.builder()
                .data(data)
                .responseCode(code)
                .responseMessage(message)
                .build();
    }
}
