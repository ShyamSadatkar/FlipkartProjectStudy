package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Category not found");
        }
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        if (!productRepository.existsById(productId)) {
            return null; // or throw an exception
        }
        product.setId(productId); // Ensure the ID is set for updating
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<String> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductsInCategory(String categoryTitle) {
        return productRepository.findByCategory_Title(categoryTitle);
    }
}
