package com.farm2pot.product.service;

import com.farm2pot.product.controller.dto.*;
import com.farm2pot.product.entity.Product;
import com.farm2pot.product.repository.ProductHistoryRepository;
import com.farm2pot.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductHistoryRepository productHistoryRepository;

    /**
     * 상품 목록 조회 (QueryDSL 동적 쿼리 사용)
     */
    public Page<ProductResponse> getProducts(String keyword, String category, String origin, Pageable pageable) {
        Page<Product> products = productRepository.getProducts(keyword, category, origin, pageable);
        return products.map(ProductResponse::from);
    }

}
