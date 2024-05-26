package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product updateProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    List<String> getAllCategories();

    List<Product> getProductsInCategory(String categoryTitle);


}
