package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

   /* @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getSingleProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }



    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Get all categories
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    // Get products by category
    @GetMapping("/category/{categoryTitle}")
    public List<Product> getProductsInCategory(@PathVariable String categoryTitle) {
        return productService.getProductsInCategory(categoryTitle);
    }

*/
   private final ProductService productServices;

    public ProductController(@Qualifier("ProductServiceImpl") ProductService productServices) {
        this.productServices = productServices;

    }
    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Long id) {
        Product product = productServices.getSingleProduct(id);
        return new ProductDto(product);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productServices.getAllProducts().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productDto.toProduct();
        Product createdProduct = productServices.createProduct(product);
        return new ProductDto(createdProduct);
    }

    @PutMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = productDto.toProduct();
        Product updatedProduct = productServices.updateProduct(id, product);
        return new ProductDto(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productServices.deleteProduct(id);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productServices.getAllCategories();
    }

    @GetMapping("/category/{categoryTitle}")
    public List<ProductDto> getProductsInCategory(@PathVariable String categoryTitle) {
        return productServices.getProductsInCategory(categoryTitle).stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }


}

