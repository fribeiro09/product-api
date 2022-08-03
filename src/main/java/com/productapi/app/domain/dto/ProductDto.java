package com.productapi.app.domain.dto;

import com.productapi.app.domain.Product;
import com.productapi.app.domain.enums.ProductStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDto {

    private Long productId;
    private String title;
    private String description;
    private ProductStatusEnum status;
    private BigDecimal price;
    private BigDecimal priceComparison;
    private List<ProductCategoriesDto> productCategories;
    private List<ProductTagsDto> productTags;

    public static ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        if (product != null) {
            BeanUtils.copyProperties(product, productDto);
        }
        if (product.getProductCategories() != null && product.getProductCategories().size() > 0) {
            List<ProductCategoriesDto> productCategoriesDtoList = product.getProductCategories().stream().map(ProductCategoriesDto::fromProductCategories).collect(Collectors.toList());
            productDto.setProductCategories(productCategoriesDtoList);
        }
        if (product.getProductTags() != null && product.getProductTags().size() > 0) {
            List<ProductTagsDto> productTagsDtoList = product.getProductTags().stream().map(ProductTagsDto::fromProductTags).collect(Collectors.toList());
            productDto.setProductTags(productTagsDtoList);
        }
        return productDto;
    }

}
