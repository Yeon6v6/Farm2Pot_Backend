package com.farm2pot.common.config;

import org.mapstruct.*;

/**
 * packageName    : com.farm2pot.common.config
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@MapperConfig(
        componentModel = "spring",                     // Spring Bean으로 등록
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // 매핑되지 않은 필드 무시
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        builder = @Builder(disableBuilder = false)
)
public interface MapStructConfig {
}
