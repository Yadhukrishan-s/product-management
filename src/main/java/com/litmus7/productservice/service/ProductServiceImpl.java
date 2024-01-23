package com.litmus7.productservice.service;

import com.litmus7.productservice.exception.ProductNotfoundException;
import com.litmus7.productservice.exception.EmptyListException;
import com.litmus7.productservice.product.Product;
import com.litmus7.productservice.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProduct() {

        List<Product> allProducts = productRepository.findAll();
        if (allProducts.isEmpty()) {
            throw new EmptyListException("Product list is empty");
        }
        log.debug("ProductServiceImpl::findAll::Total products found: {}", allProducts.size());
        return allProducts;
    }

    @Override
    public Product addProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        log.info("ProductServiceImpl::addProduct::Product added successfully. Product ID: {}", savedProduct.getId());
        return savedProduct;
    }

    @Override
    public Product deleteById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotfoundException("user with id " + id + " not found"));
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public Product findOneProduct(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotfoundException("user with id  " + id + " not found"));
    }

}
