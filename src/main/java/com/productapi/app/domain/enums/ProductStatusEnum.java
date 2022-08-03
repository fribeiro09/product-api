package com.productapi.app.domain.enums;

import com.productapi.app.util.EnumConverter;
import com.productapi.app.web.base.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements Serializable, Code<ProductStatusEnum> {

    ACTIVE("A", "Ativo"),
    DRAFT("D", "Rascunho");

    private String code;
    private String value;

    public static ProductStatusEnum getByCd(String cd) {
        return EnumConverter.getByCode(cd, values());
    }

    public static class Converter implements AttributeConverter<ProductStatusEnum, String> {

        @Override
        public String convertToDatabaseColumn(ProductStatusEnum code) {
            return code == null ? null : code.getCode();
        }

        @Override
        public ProductStatusEnum convertToEntityAttribute(String s) {
            return getByCd(s);
        }
    }
}
