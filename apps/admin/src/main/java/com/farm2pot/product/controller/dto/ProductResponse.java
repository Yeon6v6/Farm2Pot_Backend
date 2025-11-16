package com.farm2pot.product.controller.dto;

import com.farm2pot.product.entity.Product;

import java.time.LocalDateTime;

public record ProductResponse(
        Long productId,
        String code,
        String name,
        Integer price,
        Integer qty,
        String weight,
        String category,
        String origin,
        String imageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getPrice(),
                product.getStock() != null ? product.getStock().getQuantity() : 0,
                product.getWeight(),
                product.getCategory(),
                product.getOrigin(),
                null, // TODO: 이미지 처리
                product.getCreateAt(),
                product.getUpdateAt()
        );
    }
}
