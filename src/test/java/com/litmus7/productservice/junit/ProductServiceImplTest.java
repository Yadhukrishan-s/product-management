package com.litmus7.productservice.junit;

import com.litmus7.productservice.exception.ProductNotfoundException;
import com.litmus7.productservice.product.Product;
import com.litmus7.productservice.product.ProductRepository;
import com.litmus7.productservice.exception.EmptyListException;
import com.litmus7.productservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;


    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<Product>();
        Product product = new Product(1, "yadhu", 10.0);
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(product, productServiceImpl.getAllProduct().get(0));
    }

    @Test
    public void testGetAllProducts_EmptyList() {
        List<Product> products = new ArrayList<Product>();
        when(productRepository.findAll()).thenReturn(products);
        EmptyListException exception = assertThrows(EmptyListException.class, () -> productServiceImpl.getAllProduct());
        assertEquals("Product list is empty", exception.getMessage());
    }

    @Test
    public void testFindOneProduct() {
        Product product = new Product(1, "yadhu", 10.12);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        Assertions.assertEquals(product, productServiceImpl.findOneProduct(1));
    }

    @Test
    public void testDeleteProductById() {

        Product product = new Product(1, "yadhu", 10.12);
        given(productRepository.findById(1)).willReturn(Optional.of(product));

        productServiceImpl.deleteById(1);

        then(productRepository).should().findById(1);
        then(productRepository).should().deleteById(1);
    }

    @Test
    public void testDeleteProductById_NotFound() {
        given(productRepository.findById(anyInt())).willReturn(Optional.empty());

        assertThrows(ProductNotfoundException.class, () -> productServiceImpl.deleteById(1));

        then(productRepository).should().findById(1);
        then(productRepository).should(never()).deleteById(anyInt());
    }

}