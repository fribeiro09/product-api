package com.productapi.app.domain.dto;

import lombok.Data;

@Data
public class FilterDto {

    private String status;
    private String title;
    private String sortField;

}
