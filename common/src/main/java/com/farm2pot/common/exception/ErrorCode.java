package com.farm2pot.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    PRODUCT_NOT_FOUND("상품을 찾을 수 없습니다"),
    COVERAGE_NOT_FOUND("담보를 찾을 수 없습니다"),
    CONTRACT_NOT_FOUND("계약을 찾을 수 없습니다"),
    INVALID_CONTRACT_PERIOD("유효하지 않은 계약기간입니다"),
    CONTRACT_EXPIRED("기간만료 상태에서는 계약을 수정할 수 없습니다"),
    CONTRACT_ALREADY_STARTED("계약이 시작된 후에는 계약기간을 변경할 수 없습니다"),
    INVALID_REQUEST("잘못된 요청입니다"),
    INVALID_COVERAGE_SET("유효하지 않은 담보 세트입니다");

    private final String message;
}
