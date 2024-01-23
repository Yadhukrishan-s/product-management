package com.litmus7.productservice.controller;

import com.litmus7.productservice.product.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ProductController {


    @GetMapping
    public ResponseEntity<List<Product>> retriveAllProducts();

    @GetMapping("/product")
    public ResponseEntity<Product> retriveProduct(@RequestParam Integer id);

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product);


    @DeleteMapping("product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id);


}
