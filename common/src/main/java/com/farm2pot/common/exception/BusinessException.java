package com.farm2pot.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final DomainErrorCode errorCode;

    public BusinessException(DomainErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(DomainErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
