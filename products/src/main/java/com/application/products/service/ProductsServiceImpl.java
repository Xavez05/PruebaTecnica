package com.application.products.service;

import com.application.products.dto.ProductDTO;
import com.application.products.repository.ProductRepository;
import com.application.products.utils.ConvertData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductsServiceImpl.class);
   private ProductRepository productRepository;
   public ConvertData convertData = new ConvertData();


   public ProductsServiceImpl(ProductRepository productRepository) {
       this.productRepository = productRepository;
   }

    @Override
    public List<ProductDTO> getAllProducts() {
        return convertData.ParseToDTO(productRepository.getAllProducts());
    }

    @Override
    public ProductDTO getProductById(int id) {
        return convertData.ParseToDTO(productRepository.getProductById(id));
    }
}
