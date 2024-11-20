package org.mrunmayi.restaurant.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mrunmayi.restaurant.dto.ProductRequest;
import org.mrunmayi.restaurant.dto.ProductResponse;
import org.mrunmayi.restaurant.entity.Products;
import org.mrunmayi.restaurant.helpers.JWTAuthHelper;
import org.mrunmayi.restaurant.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final JWTAuthHelper jwtAuthHelper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(productService.getProductsByID(id));
    }

    @GetMapping("/price")
    public ResponseEntity<List<ProductResponse>> getProductByPrice(
            @RequestParam Double low,
            @RequestParam Double high
    ) {
        return ResponseEntity.ok(productService.getProductsByPrice(low, high));
    }

    @PostMapping("/add")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProductRequest updateRequest,
            HttpServletRequest request
    ) {
        String email = jwtAuthHelper.checkJWT(request);
        if(email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.updateProduct(id, updateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable("id") Long id,
            HttpServletRequest request
    ) {
        String email = jwtAuthHelper.checkJWT(request);
        if(email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
