package com.farm2pot.common.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    private final BusinessErrorCode errorCode;

    public DomainException(BusinessErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
