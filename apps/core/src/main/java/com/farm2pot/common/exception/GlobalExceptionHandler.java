//package com.farm2pot.common.exception;
//
//import com.farm2pot.common.response.ErrorResponse;
//import com.farm2pot.common.response.ResponseMessage;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
///**
// * packageName    : com.farm2pot.common.exception
// * author         : TAEJIN
// * date           : 2025-10-06
// * description    : Exception Handler 추가 - 추가로 정의할거면 하세용
// */
//
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//
//    @ExceptionHandler(BaseException.class)
//    public ResponseEntity<ResponseMessage<ErrorResponse>> handleCustomAuthException(
//            BaseException ex, HttpServletRequest request
//    ) {
//        String message = ex.getLocalizedMessage();
//        ErrorResponse error = ErrorResponse.of(
//                ex.getStatus(),
//                ex.getLocalizedMessage(),
//                request.getRequestURI()
//        );
//        return ResponseEntity.status(ex.getStatus())
//                .body(ResponseMessage.fail(error, message));
//    }
//    // DTO 유효성 검증 실패
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ResponseMessage<ErrorResponse>> handleValidationException(
//            MethodArgumentNotValidException ex, HttpServletRequest request
//    ) {
//        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
//        ErrorResponse error = ErrorResponse.of(
//                HttpStatus.BAD_REQUEST,
//                message,
//                request.getRequestURI()
//        );
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ResponseMessage.fail(error, message));
//    }
//
//    // 일반 예외 처리
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseMessage<ErrorResponse>> handleException(
//            Exception ex, HttpServletRequest request
//    ) {
//        String message = ex.getLocalizedMessage();
//        ErrorResponse error = ErrorResponse.of(
//                HttpStatus.INTERNAL_SERVER_ERROR,
//                message,
//                request.getRequestURI()
//        );
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ResponseMessage.fail(error, message));
//    }
//}