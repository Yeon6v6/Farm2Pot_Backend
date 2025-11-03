package com.farm2pot.common.exception;

import lombok.Getter;

/**
 * packageName    : com.farm2pot.common.exception
 * author         : TAEJIN
 * date           : 2025-10-06
 * description    : JWT 관련 exception 추가
 */
@Getter
public class UserException extends BaseException {


    public UserException(UserErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(BaseErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }
}