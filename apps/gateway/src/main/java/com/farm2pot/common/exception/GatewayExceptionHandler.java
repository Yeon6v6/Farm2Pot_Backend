package com.farm2pot.common.exception;

import com.farm2pot.common.response.ErrorResponse;
import com.farm2pot.common.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * packageName    : com.farm2pot.common.exception
 * author         : TAEJIN
 * date           : 2025-10-11
 * description    : gateway - ExceptionHandler (ServerHttpRequest)
 */

@RestControllerAdvice
public class GatewayExceptionHandler {
    @ExceptionHandler(GatewayException.class)
    public Mono<ResponseEntity<ResponseMessage<ErrorResponse>>> handleCustomException(GatewayException ex, ServerHttpRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = ErrorResponse.of(ex.getStatus(), message, request.getPath().value());
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(ResponseMessage.fail(error, message)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<ResponseMessage<ErrorResponse>>> handleValidationException(MethodArgumentNotValidException ex, ServerHttpRequest request) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponse error = ErrorResponse.of(HttpStatus.BAD_REQUEST, message, request.getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.fail(error, message)));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ResponseMessage<ErrorResponse>>> handleException(Exception ex, ServerHttpRequest request) {
        String message = ex.getLocalizedMessage();
        ErrorResponse error = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, message, request.getPath().value());
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.fail(error, message)));
    }
}
