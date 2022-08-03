package com.productapi.app.domain.dto;

import com.productapi.app.domain.Category;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CategoryDto {

    private Long categoryId;
    private String description;

    public static CategoryDto fromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        if (category != null) {
            BeanUtils.copyProperties(category, categoryDto);
        }
        return categoryDto;
    }
}
