package com.litmus7.productservice.aop;

import com.litmus7.productservice.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspectProductService {

    //return type ,class-name.method name(args)
    // @Before("**(..)")
//
    @Before("execution(* com.litmus7.productservice.service.ProductServiceImpl.*(..))")
    public void logMethodCall(JoinPoint jp) {
        log.info("ProductServiceImpl:: " + jp.getSignature().getName());
    }

    @After("execution(* com.litmus7.productservice.service.ProductServiceImpl.getAllProduct(..))")
    public void logGetAllProduct() {
        log.info("ProductServiceImpl::findAll::Total products found: {}");
    }

    @After("execution(* com.litmus7.productservice.service.ProductServiceImpl.addProduct(..)) && args(product)")
    public void logAddProduct(JoinPoint joinPoint, Product product) {
        log.info("ProductServiceImpl::addProduct::Adding a new product: {}", product);
    }

    @After("execution(* com.litmus7.productservice.service.ProductServiceImpl.deleteById(..)) && args(id)")
    public void logDeleteById(JoinPoint joinPoint, Integer id) {
        log.info("ProductServiceImpl::deleteById::Deleting product with ID: {}", id);
    }

    @After("execution(* com.litmus7.productservice.service.ProductServiceImpl.findOneProduct(..)) && args(id)")
    public void logFindOneProduct(JoinPoint joinPoint, Integer id) {
        log.info("ProductServiceImpl::findOne::Fetching product with ID: {}", id);
    }
}
