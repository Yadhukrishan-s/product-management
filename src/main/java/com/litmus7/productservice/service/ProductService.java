package com.litmus7.productservice.service;

import com.litmus7.productservice.product.Product;

import java.util.List;


public interface ProductService {

    public List<Product> getAllProduct();

    public Product addProduct(Product product);

    public Product deleteById(Integer id);

    public Product findOneProduct(Integer id);

}
