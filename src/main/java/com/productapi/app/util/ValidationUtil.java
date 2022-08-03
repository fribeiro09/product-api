package com.productapi.app.util;

import com.productapi.app.domain.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidationUtil {

    public static Boolean validateProduct(Product product) {
        boolean errors = false;

        if (ParserUtil.isEmpty(product.getTitle())) {
            errors = true;
        }
        if (ParserUtil.isEmpty(product.getDescription())) {
            errors = true;
        }
        if (ParserUtil.isEmpty(product.getStatus().getCode())) {
            errors = true;
        }
        if (product.getPrice() == null) {
            errors = true;
        }

        return errors;
    }

}
