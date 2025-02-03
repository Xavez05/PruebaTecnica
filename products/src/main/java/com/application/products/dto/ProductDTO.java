package com.application.products.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String image;
}
