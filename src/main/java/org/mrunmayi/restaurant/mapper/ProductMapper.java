package org.mrunmayi.restaurant.mapper;

import org.mrunmayi.restaurant.dto.ProductDto.ProductRequest;
import org.mrunmayi.restaurant.dto.ProductDto.ProductResponse;
import org.mrunmayi.restaurant.entity.Products;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Products toProductEntity(ProductRequest.ProductCreateRequest request) {
        return Products.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public ProductResponse toProductResponse(Products products) {
        return new ProductResponse(
                products.getName(),
                products.getPrice()
        );
    }
}
