package org.mrunmayi.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.ProductDto.ProductRequest;
import org.mrunmayi.restaurant.dto.ProductDto.ProductResponse;
import org.mrunmayi.restaurant.entity.Products;
import org.mrunmayi.restaurant.exception.NotFoundException;
import org.mrunmayi.restaurant.mapper.ProductMapper;
import org.mrunmayi.restaurant.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo repo;
    private final ProductMapper mapper;

    public String createProduct(ProductRequest.ProductCreateRequest request) {
        Products product = mapper.toProductEntity(request);
        repo.save(product);
        return "New Product Added Successfully";
    }

    public ProductResponse getProductsByID(Long id) {
        Products product = repo.findById(id).orElseThrow(() -> new NotFoundException(
                format("No product found with the ID %s", id)
        ));
        return mapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Products> products = repo.findAll();
        return products.stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public List<ProductResponse> getProductsByPrice(Double low, Double high) {
        List<Products> products = repo.getProductByPrice(low, high);
        return products.stream()
                .map(mapper::toProductResponse)
                .toList();
    }

    public ProductResponse updateProduct(
            Long id,
            ProductRequest.ProductUpdateRequest updateRequest
    ) {
        Products product = repo.findById(id).orElseThrow(() -> new NotFoundException(
                format("No product found with the ID %s", id)
        ));
        if(updateRequest.name() != null){
            product.setName(updateRequest.name());
        }
        if(updateRequest.price() != null){
            product.setPrice(updateRequest.price());
        }
        repo.save(product);
        return mapper.toProductResponse(product);
    }

    public String deleteProduct( Long id ) {
        repo.findById(id).orElseThrow(() -> new NotFoundException(
                format("No product found with the ID %s", id)
        ));
        repo.deleteById(id);
        return "Product Deleted Successfully";
    }
}
