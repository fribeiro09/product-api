package com.productapi.app.domain;

import com.productapi.app.domain.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "description", nullable = false, length = 8000)
    private String description;

    @Convert(converter = ProductStatusEnum.Converter.class)
    @Column(name = "status", nullable = false, length = 1)
    private ProductStatusEnum status;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "price_comparison")
    private BigDecimal priceComparison;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private List<ProductCategories> productCategories;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private List<ProductTags> productTags;

}
