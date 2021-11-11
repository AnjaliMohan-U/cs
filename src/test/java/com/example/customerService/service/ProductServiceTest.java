package com.example.customerService.service;

import com.example.customerService.Details;
import com.example.customerService.entity.InventoryEntity;
import com.example.customerService.entity.SkuEntity;
import com.example.customerService.model.InventoryModel;
import com.example.customerService.model.PriceModel;
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

import java.util.Optional;

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

    @Test
    public void checkPrice(){
        PriceModel price = Details.getPriceModel();
        Optional<SkuEntity> sku = Details.productsEntity().getSkuEntityList().stream().filter(x -> x.getSkuCode().equals("s1")).findFirst();
//        System.out.println(sku.get().getSize());
        when(skuRepository.findBySkuCode("s1")).thenReturn(sku.get());
        when(skuRepository.save(sku.get())).thenReturn(sku.get());
        assertEquals(sku.get().getSkuCode(), productService.addPrice("s1",price).getSkuCode());
    }

    @Test
    public void checkInventory(){
        InventoryModel inventory = Details.getInventory();
//        System.out.println(inventory.getQuantityAvailable());
        Optional<SkuEntity> sku = Details.productsEntity().getSkuEntityList().stream().filter(x -> x.getSkuCode().equals("s1")).findFirst();
//        System.out.println(sku.get().getSize());
        when(skuRepository.findBySkuCode("s1")).thenReturn(sku.get());
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Inventory added successfully!"), productService.addStock("s1",inventory));
    }

}
