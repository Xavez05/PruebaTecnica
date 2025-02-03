package com.application.products.repository;

import com.application.products.model.Product;


import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(int id);
}
