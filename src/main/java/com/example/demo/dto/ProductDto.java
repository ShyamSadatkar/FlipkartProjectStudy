package com.example.demo.dto;

import com.example.demo.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDto category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        if (product.getCategory() != null) {
            this.category = new CategoryDto(product.getCategory());
        }
    }

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        if (this.category != null) {
            product.setCategory(this.category.toCategory());
        }
        return product;
    }
}
