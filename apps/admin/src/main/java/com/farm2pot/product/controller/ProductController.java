package com.farm2pot.product.controller;

import com.farm2pot.common.http.request.CustomPageRequest;
import com.farm2pot.product.controller.dto.*;
import com.farm2pot.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    /**
     * 상품 목록 조회
     */
    @GetMapping
    public Page<ProductResponse> getProducts(
            @RequestParam String keyword,
            @RequestParam String category,
            @RequestParam String origin,
            CustomPageRequest pageRequest
    ) {
        return productService.getProducts(keyword, category, origin, pageRequest.getPageable());
    }

    /**
     * 상품 상세 조회
     */
    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }
}
