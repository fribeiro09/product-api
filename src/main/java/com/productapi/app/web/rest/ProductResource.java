package com.productapi.app.web.rest;

import com.productapi.app.domain.dto.FilterDto;
import com.productapi.app.domain.dto.ProductDto;
import com.productapi.app.domain.enums.ProductStatusEnum;
import com.productapi.app.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        productDto = this.productService.createProduct(productDto);
        return productDto;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<ProductDto> getProducts(FilterDto filterDto) {
        List<ProductDto> productDtoList = this.productService.getProducts(filterDto);
        return productDtoList;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto getProductById(@PathVariable(value = "productId") Long productId) {
        ProductDto productDto = this.productService.getProductById(productId);
        return productDto;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@PathVariable(value = "productId") Long productId, @RequestBody ProductDto productDto) {
        productDto = this.productService.updateProduct(productId, productDto);
        return productDto;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto disableProductById(@PathVariable(value = "productId") Long productId) {
        ProductDto productDto = this.productService.changeProductStatusById(productId, ProductStatusEnum.DRAFT);
        return productDto;
    }

    @RequestMapping(value = "/{productId}/activate", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto activateProductById(@PathVariable(value = "productId") Long productId) {
        ProductDto productDto = this.productService.changeProductStatusById(productId, ProductStatusEnum.ACTIVE);
        return productDto;
    }

}
