package com.farm2pot.product.controller.dto.mapstruct;

import com.farm2pot.product.controller.dto.ProductResponse;
import com.farm2pot.product.service.dto.ProductQueryResult;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductDtoMapper {

    ProductResponse toResponseList(ProductQueryResult result);
    List<ProductResponse> toResponseList(List<ProductQueryResult> results);

}
