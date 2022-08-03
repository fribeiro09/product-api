package com.productapi.app.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_categories")
public class ProductCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_categories_id")
    private Long ProductCategoriesId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "category", nullable = false, length = 200)
    private String category;

}
