package com.litmus7.productservice.aop;

import com.litmus7.productservice.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspectProductController {

    @Around("execution(* com.litmus7.productservice.controller.ProductControllerImpl.*(..))")
    public Object logMethodCall(ProceedingJoinPoint jp) throws Throwable {
        log.info("Request received for method: {}", jp.getSignature().getName());
        Object result = jp.proceed();
        log.info("ProductServiceImpl::Response from method: {}", result);

        return result;
    }


    @Before("execution(* com.litmus7.productservice.controller.ProductControllerImpl.retriveAllProducts(..))")
    public void logRetriveAllProducts() {
        log.info("ProductControllerImpl::retriveAllProducts::Retrieving all products.");

    }

    @Before("execution(* com.litmus7.productservice.controller.ProductControllerImpl.retriveProduct(..)) && args(id)")
    public void logRetriveProduct(JoinPoint pt, Integer id) {
        log.info("ProductControllerImpl::retriveProduct::Retrieving product with id: {}", id);
    }


    @Before("execution(* com.litmus7.productservice.controller.ProductControllerImpl.createProduct(..)) && args(product)")
    public void logCreateProduct(JoinPoint jp, Product product) {
        log.info("ProductControllerImpl::createProduct::Creating a new product: {}", product);

    }


    @Before("execution(* com.litmus7.productservice.controller.ProductControllerImpl.deleteProduct(..)) && args(id)")
    public void logDeleteProduct(JoinPoint jp, Integer id) {
        log.info("ProductControllerImpl::deleteProduct::Deleting product with id: {}", id);
    }


}
