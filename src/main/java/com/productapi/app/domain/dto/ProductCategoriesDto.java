package com.productapi.app.domain.dto;

import com.productapi.app.domain.ProductCategories;
import com.productapi.app.domain.enums.ProductStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
public class ProductCategoriesDto {

    private Long ProductCategoriesId;
    private Long productId;
    private String category;

    public static ProductCategoriesDto fromProductCategories(ProductCategories productCategories) {
        ProductCategoriesDto productCategoriesDto = new ProductCategoriesDto();
        if (productCategories != null) {
            BeanUtils.copyProperties(productCategories, productCategoriesDto);
        }
        return productCategoriesDto;
    }

}
