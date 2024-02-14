package com.dekankilic.rediscache.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    private Long id;
    private Double price;
    private int stock;
}
