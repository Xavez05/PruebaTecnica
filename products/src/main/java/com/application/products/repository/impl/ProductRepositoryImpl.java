package com.application.products.repository.impl;

import com.application.products.model.Product;
import com.application.products.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    public final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    private final String url;

    public ProductRepositoryImpl(RestTemplate restTemplate, @Value("${url.api.products}") String url) {
        this.url = url;
        this.restTemplate = restTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        Product[] products = restTemplate.getForObject(url, Product[].class);
        logger.info(products.length + " products found");
        if (products.length > 0) {
            return Arrays.asList(products);
        }
        return new ArrayList<>();
    }

    @Override
    public Product getProductById(int id) {
        Product products = restTemplate.getForObject(url + "/"+ id, Product.class);
        if (products != null) {
            return products;
        }
        return null;
    }

}
