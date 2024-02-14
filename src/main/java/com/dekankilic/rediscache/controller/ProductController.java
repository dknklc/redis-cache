package com.dekankilic.rediscache.controller;

import com.dekankilic.rediscache.dto.ProductCreateRequest;
import com.dekankilic.rediscache.dto.ProductUpdateRequest;
import com.dekankilic.rediscache.entity.Product;
import com.dekankilic.rediscache.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateRequest productCreateRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.createProduct(productCreateRequest));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody ProductUpdateRequest productUpdateRequest){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.updateProduct(productUpdateRequest));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.deleteProduct(productId));
    }
}
