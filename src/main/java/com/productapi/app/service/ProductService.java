package com.productapi.app.service;

import com.productapi.app.domain.Product;
import com.productapi.app.domain.ProductCategories;
import com.productapi.app.domain.ProductTags;
import com.productapi.app.domain.dto.FilterDto;
import com.productapi.app.domain.dto.ProductCategoriesDto;
import com.productapi.app.domain.dto.ProductDto;
import com.productapi.app.domain.enums.ProductStatusEnum;
import com.productapi.app.repository.ProductRepository;
import com.productapi.app.util.ParserUtil;
import com.productapi.app.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) {
        if (productDto == null) {
            throw new IllegalArgumentException();
        }

        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);


        List<ProductCategories> productCategoriesList = new ArrayList<>();
        if (productDto.getProductCategories() != null && productDto.getProductCategories().size() > 0) {
            productDto.getProductCategories().forEach(productCategoriesDto -> {
                ProductCategories productCategories = new ProductCategories();
                BeanUtils.copyProperties(productCategoriesDto, productCategories);
                productCategoriesList.add(productCategories);
            });
            product.setProductCategories(productCategoriesList);
        }


        List<ProductTags> productTagsList = new ArrayList<>();
        if (productDto.getProductTags() != null && productDto.getProductTags().size() > 0) {
            productDto.getProductTags().forEach(productTagsDto -> {
                ProductTags productTags = new ProductTags();
                BeanUtils.copyProperties(productTagsDto, productTags);
                productTagsList.add(productTags);
            });
            product.setProductTags(productTagsList);
        }

        if (ValidationUtil.validateProduct(product)) {
            throw new IllegalArgumentException();
        }
        product = this.productRepository.save(product);

        return ProductDto.fromProduct(product);
    }

    public ProductDto getProductById(Long productId) {
        if (productId == null) {
            throw new IllegalArgumentException();
        }

        Product product = this.productRepository.findByProductId(productId);
        if (product == null) {
            throw new IllegalArgumentException();
        }
        ProductDto productDto = ProductDto.fromProduct(product);

        return productDto;
    }

    public List<ProductDto> getProducts(FilterDto filterDto) {

        List<Product> productList = this.productRepository.findProductsByParameters(filterDto.getStatus(), filterDto.getTitle(), filterDto.getSortField());
        List<ProductDto> productDtoList = productList.stream().map(ProductDto::fromProduct).collect(Collectors.toList());

        return productDtoList;
    }

    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        if (productId == null || productDto == null) {
            throw new IllegalArgumentException();
        }

        Product product = this.productRepository.findByProductId(productId);
        if (product == null) {
            throw new IllegalArgumentException();
        }

        BeanUtils.copyProperties(productDto, product);
        if (ValidationUtil.validateProduct(product)) {
            throw new IllegalArgumentException();
        }
        product = productRepository.save(product);

        return ProductDto.fromProduct(product);
    }

    public ProductDto changeProductStatusById(Long productId, ProductStatusEnum status) {
        if (productId == null) {
            throw new IllegalArgumentException();
        }
        Product product = productRepository.findByProductIdAndStatusNot(productId, status);

        if (product == null) {
            throw new IllegalArgumentException();
        }
        product.setStatus(status);
        product = this.productRepository.save(product);

        return ProductDto.fromProduct(product);
    }

}
