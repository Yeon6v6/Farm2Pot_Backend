package com.farm2pot.common.config;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * packageName    : com.farm2pot.common.config
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    :
 */
@MapperConfig(
        componentModel = "spring",                     // Spring Bean으로 등록
        unmappedTargetPolicy = ReportingPolicy.IGNORE, // 매핑되지 않은 필드 무시
        builder = @Builder(disableBuilder = false)
)
public interface MapStructConfig {
}
