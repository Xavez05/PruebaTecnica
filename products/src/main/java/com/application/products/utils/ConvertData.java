package com.application.products.utils;

import com.application.products.dto.ProductDTO;
import com.application.products.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConvertData {

    private static final Logger logger = LoggerFactory.getLogger(ConvertData.class);

    public List<ProductDTO> ParseToDTO(List<Product> products) {
        logger.info("Parsing products: "+ products.size());


        if (products.isEmpty()) {
            return new ArrayList<>();
        }


        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setCategory(product.getCategory());
            productDTO.setTitle(product.getTitle());
            productDTO.setPrice(product.getPrice());
            productDTO.setImage(product.getImage());

            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public ProductDTO ParseToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setCategory(product.getCategory());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setImage(product.getImage());

        return productDTO;
    }
}
