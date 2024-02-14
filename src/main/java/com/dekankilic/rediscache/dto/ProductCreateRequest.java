package com.dekankilic.rediscache.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
    private String productName;
    private Double price;
    private int stock;
}
