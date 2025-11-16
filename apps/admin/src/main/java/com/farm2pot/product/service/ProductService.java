package com.farm2pot.product.service;

import com.farm2pot.common.exception.BusinessException;
import com.farm2pot.common.exception.ErrorCode;
import com.farm2pot.product.controller.dto.*;
import com.farm2pot.product.entity.Product;
import com.farm2pot.product.entity.Stock;
import com.farm2pot.product.repository.ProductHistoryRepository;
import com.farm2pot.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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

    /**
     * 상품 상세 조회
     */
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        return ProductResponse.from(product);
    }

    /**
     * 상품 등록
     */
    @Transactional
    public CreateProductResponse createProduct(CreateProductRequst request) {
        // 상품 코드 생성 (제공되지 않은 경우)
        String productCode = request.code();
        if (productCode == null || productCode.isBlank()) {
            productCode = generateProductCode();
        }

        // 재고 초기화
        Stock stock = Stock.builder()
                .quantity(request.initialQty() != null ? request.initialQty() : 0)
                .build();

        // 상품 생성
        Product product = Product.builder()
                .code(productCode)
                .name(request.name())
                .price(request.price())
                .weight(request.weight())
                .origin(request.origin())
                .category(request.category())
                .stock(stock)
                .build();

        Product savedProduct = productRepository.save(product);

        return new CreateProductResponse(
                savedProduct.getId(),
                savedProduct.getCode(),
                savedProduct.getName(),
                savedProduct.getCreateAt()
        );
    }

    /**
     * 상품 수정
     */
    @Transactional
    public UpdateProductResponse updateProduct(Long productId, UpdateProductRequst request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        Product updatedProduct = Product.builder()
                .id(product.getId())
                .code(request.code())
                .name(request.name())
                .price(request.price())
                .weight(request.weight())
                .origin(request.origin())
                .category(request.category())
                .stock(product.getStock())
                .build();

        Product saved = productRepository.save(updatedProduct);

        return new UpdateProductResponse(
                saved.getId(),
                saved.getCode(),
                saved.getName(),
                saved.getUpdateAt()
        );
    }

    /**
     * 상품 삭제
     */
    @Transactional
    public String deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        productRepository.delete(product);

        return LocalDateTime.now().toString();
    }

    /**
     * 상품 코드 생성
     */
    private String generateProductCode() {
        return "PRD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
