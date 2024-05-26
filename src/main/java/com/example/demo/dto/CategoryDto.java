package com.example.demo.dto;

import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
    }

    public Category toCategory() {
        Category category = new Category();
        category.setId(this.id);
        category.setTitle(this.title);
        return category;
    }
}

