package com.dekankilic.rediscache.mapper;


import com.dekankilic.rediscache.dto.ProductCreateRequest;
import com.dekankilic.rediscache.dto.ProductUpdateRequest;
import com.dekankilic.rediscache.entity.Product;


public class ProductMapper {
    public static ProductCreateRequest mapToProductCreateRequest(Product product, ProductCreateRequest productCreateRequest){
        productCreateRequest.setProductName(product.getProductName());
        productCreateRequest.setStock(product.getStock());
        productCreateRequest.setPrice(product.getPrice());
        return productCreateRequest;
    }

    public static Product mapToProductFromCreateRequest(Product product, ProductCreateRequest productCreateRequest){
        product.setProductName(productCreateRequest.getProductName());
        product.setStock(productCreateRequest.getStock());
        product.setPrice(productCreateRequest.getPrice());
        return product;
    }

    public static Product mapToProductFromUpdateRequest(Product product, ProductUpdateRequest productUpdateRequest){
        product.setProductName(product.getProductName());
        product.setStock(productUpdateRequest.getStock());
        product.setPrice(productUpdateRequest.getPrice());
        return product;
    }
}
