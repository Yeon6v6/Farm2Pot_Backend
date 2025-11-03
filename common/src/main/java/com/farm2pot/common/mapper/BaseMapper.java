package com.farm2pot.common.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * packageName    : com.farm2pot.common
 * author         : TAEJIN
 * date           : 2025-10-04
 * description    : MapStruct 사용 > entity <-> record 전환가능
 */
public interface BaseMapper<E, D> {
    @Mapping(target = "id", ignore = true) /* ID는 null이어도 가능 */
    E toEntity(D dto); /* create - 생성용도 */
    D toDto(E entity);  /* read - 조회용도 */
    void updateEntityFromDto(D dto, @MappingTarget E entity); /* update - dto를 entity에 덮어씀 자동 update */
}
