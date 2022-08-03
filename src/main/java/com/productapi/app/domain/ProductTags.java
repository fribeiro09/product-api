package com.productapi.app.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_tags")
public class ProductTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_tags_id")
    private Long productTagsId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tag", nullable = false, length = 200)
    private String tag;

}
