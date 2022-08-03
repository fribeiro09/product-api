package com.productapi.app.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {

    private String status;
    private String title;
    private String sortField;

}
