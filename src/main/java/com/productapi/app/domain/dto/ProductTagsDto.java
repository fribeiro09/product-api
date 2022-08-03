package com.productapi.app.domain.dto;

import com.productapi.app.domain.ProductTags;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ProductTagsDto {

    private Long productTagsId;
    private Long productId;
    private String tag;

    public static ProductTagsDto fromProductTags(ProductTags productTags) {
        ProductTagsDto productTagsDto = new ProductTagsDto();
        if (productTags != null) {
            BeanUtils.copyProperties(productTags, productTagsDto);
        }
        return productTagsDto;
    }

}
