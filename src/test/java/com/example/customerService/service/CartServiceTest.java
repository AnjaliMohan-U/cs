package com.example.customerService.service;

import com.example.customerService.Details;
import com.example.customerService.entity.AddressEntity;
import com.example.customerService.entity.CustomerEntity;
import com.example.customerService.entity.SkuEntity;
import com.example.customerService.model.CartModel;
import com.example.customerService.model.OrderModel;
import com.example.customerService.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @InjectMocks
    CartService cartService;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private SkuRepository skuRepository;
    @Mock
    private InventoryRepository inventoryRepository;
    @Mock
    private PriceRepository priceRepository;
    @Mock
    private OrderRepository orderRepository;

    @Test
    public void checkProduct(){
        CartModel cartModel = Details.getCartModel();
        when(customerRepository.findByEmail("anjali@gmail.com")).thenReturn(Details.getCustomerEntity());
        when(productRepository.findByProductCode("p1")).thenReturn(Details.productsEntity());
        Optional<SkuEntity> sku = Details.productsEntity().getSkuEntityList().stream().filter(x -> x.getSkuCode().equals("s1")).findFirst();
        when(skuRepository.findBySkuCode("s1")).thenReturn(sku.get());
        when(inventoryRepository.findBySkuEntity(sku.get())).thenReturn(sku.get().getInventoryEntity());
        assertEquals( ResponseEntity.status(HttpStatus.CREATED).body("product added to cart"), cartService.addProductToCart("anjali@gmail.com", cartModel));
    }

//    @Test
//    public void checkPlaceOrder(){
//        OrderModel orderModel = Details.getOrderModel();
//        CustomerEntity customer = Details.getCustomerEntity();
//        when(customerRepository.findByEmail("anjali@gmail.com")).thenReturn(Details.getCustomerEntity());
//        when(cartRepository.findByCustomerEntity(customerRepository.findByEmail("anjali@gmail.com"))).thenReturn(Details.getCartEntities());
//        when(customer.getAddressEntityList().stream().filter(a -> a.getAddressId().equals(1l)).findFirst()).thenReturn(Details.getCustomerEntity().getAddressEntityList().stream().filter(x->x.getAddressId().equals(1l)).findFirst());
//        Optional<SkuEntity> sku = Details.productsEntity().getSkuEntityList().stream().filter(x -> x.getSkuCode().equals("s1")).findFirst();
//        when(skuRepository.findBySkuCode("s1")).thenReturn(sku.get());
//        when(inventoryRepository.findBySkuEntity(sku.get())).thenReturn(sku.get().getInventoryEntity());
//        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("order placed" +"\n" + "order status : " + orderModel.getOrderStatus()),
//                cartService.placeOrder("anjali@gmail.com", 1l));
//    }

}
