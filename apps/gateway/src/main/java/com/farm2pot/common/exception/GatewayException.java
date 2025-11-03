package com.farm2pot.common.exception;

import lombok.Getter;

/**
 * packageName    : com.farm2pot.jwt.exception
 * author         : TAEJIN
 * date           : 2025-10-11
 * description    :
 */

@Getter
public class GatewayException extends BaseException {
    public GatewayException(GatewayErrorCode errorCode) {
        super(errorCode);
    }

    public GatewayException(BaseErrorCode errorCode, String customMessage) {
        super(errorCode, customMessage);
    }
}
