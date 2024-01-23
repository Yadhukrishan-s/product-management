package com.litmus7.productservice.controller;

import com.litmus7.productservice.product.Product;
import com.litmus7.productservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Validated
@Slf4j
public class ProductControllerImpl implements ProductController {

    public final ProductService productService;

    public ProductControllerImpl(ProductService productServiceImpl) {
        this.productService = productServiceImpl;
    }

    @Override
    public ResponseEntity<List<Product>> retriveAllProducts() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> retriveProduct(Integer id) {
        return new ResponseEntity<>(productService.findOneProduct(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Product> deleteProduct(Integer id) {

        return new ResponseEntity<>(productService.deleteById(id), HttpStatus.OK);
    }
}


