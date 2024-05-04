package com.dekankilic.rediscache.service;

import com.dekankilic.rediscache.dto.ProductCreateRequest;
import com.dekankilic.rediscache.dto.ProductUpdateRequest;
import com.dekankilic.rediscache.entity.Product;
import com.dekankilic.rediscache.mapper.ProductMapper;
import com.dekankilic.rediscache.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(ProductService.class);


    @CacheEvict(value = "products", allEntries = true, cacheManager = "cacheManager")
    public Product createProduct(ProductCreateRequest productCreateRequest){
        Product newProduct = ProductMapper.mapToProductFromCreateRequest(new Product(), productCreateRequest);
        return productRepository.save(newProduct);
    }

    @Cacheable(value = "products", key = "#root.methodName", unless = "#result == null", cacheManager = "cacheManager")
    public List<Product> getAllProducts(){
        logger.info("Return from db");
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#root.methodName + #productId", unless = "#result == null", cacheManager = "cacheManager")
    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

    @CacheEvict(value = "products", allEntries = true, cacheManager = "cacheManager")
    public String updateProduct(ProductUpdateRequest productUpdateRequest){
        Optional<Product> product = productRepository.findById(productUpdateRequest.getId());
        if(product.isPresent()){
            productRepository.save(ProductMapper.mapToProductFromUpdateRequest(product.get(), productUpdateRequest));
            return "Product Updated";
        } else{
            return "Product not found";
        }
    }

    @CacheEvict(value = "products", allEntries = true)
    public String deleteProduct(Long productId){
        productRepository.deleteById(productId);
        return "Product deleted";
    }
}
