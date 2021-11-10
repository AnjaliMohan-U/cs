package com.example.customerService.service;

import com.example.customerService.Details;
import com.example.customerService.model.ProductModel;
import com.example.customerService.repository.ProductRepository;
import com.example.customerService.repository.SkuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    SkuRepository skuRepository;
    @InjectMocks
    ProductService productService;

    @Test
    public void checkProduct(){
        ProductModel p = Details.getProductModel();
       // when(productRepository.save()).thenReturn("yes");
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("product added"), productService.addProducts(p));
    }

    @Test
    public void checkSku(){
        when(productRepository.findByProductCode("p1")).thenReturn(Details.productsEntity());
        when(productRepository.save(productRepository.findByProductCode("p1"))).thenReturn(Details.productsEntity());
        assertEquals(Details.productsEntity().getProductCode(), productService.addSkus("p1",Details.getSkuModel()).getProductCode());
    }
//
//    @Test
//    public void checkPrice(){
//        when(skuRepository.findBySkuCode("s1")).thenReturn(Details.productsEntity());
//        assertEquals(Details.productsEntity(), productService.addPrice("s1", Details.getPriceModel()));
//    }

}
