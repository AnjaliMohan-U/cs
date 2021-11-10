package com.example.customerService.service;

import com.example.customerService.entity.*;
import com.example.customerService.model.CartModel;
import com.example.customerService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private PriceRepository priceRepository;

    public ResponseEntity<String> addProductToCart(String email, CartModel cartModel) {
        CustomerEntity customer = customerRepository.findByEmail(email);
        if(customer!=null){
            ProductsEntity product = productRepository.findByProductCode(cartModel.getProductCode());
            if(product!=null){
                SkuEntity sku = skuRepository.findBySkuCode(cartModel.getSkuCode());
                if(sku!=null){
                    InventoryEntity inventoryEntity = inventoryRepository.findBySkuEntity(sku);
                    if(Integer.parseInt(inventoryEntity.getQuantityAvailable())>=Integer.parseInt(cartModel.getQuantity())){
                        List<CartEntity> cartEntities = new ArrayList<>();
                        CartEntity cart = new CartEntity();
                        cart.setProductCode(cartModel.getProductCode());
                        cart.setSkuCode(cartModel.getSkuCode());
                        cart.setQuantity(cartModel.getQuantity());
                        cart.setCustomerEntity(customer);
                        cartEntities.add(cart);
                        customer.setCartEntities(cartEntities);
                        customerRepository.save(customer);
                        return ResponseEntity.status(HttpStatus.CREATED).body("product added to cart");
                    }
                    else if(Integer.parseInt(inventoryEntity.getQuantityAvailable())<Integer.parseInt(cartModel.getQuantity())){
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(Integer.parseInt(cartModel.getQuantity())+" : sorry, required quantity is not available\n" + "Quantity available: " + inventoryEntity.getQuantityAvailable());
                    }
                    else{
                        return ResponseEntity.status(HttpStatus.CREATED).body("Product Out of Stock");
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("sorry, product not added to cart");
    }

    public String viewCart(String email) {
        List<CartEntity> cartProducts = cartRepository.findByCustomerEntity(customerRepository.findByEmail(email));
        AtomicReference<String> viewCart = new AtomicReference<>("");
        AtomicReference<Long> total= new AtomicReference<>(0l);
        cartProducts.stream().forEach(cartProduct ->{
            ProductsEntity product = productRepository.findByProductCode(cartProduct.getProductCode());
            SkuEntity sku = skuRepository.findBySkuCode(cartProduct.getSkuCode());
            PriceEntity price = priceRepository.findBySkuEntity(sku);
            System.out.println(product.getProductName());
            System.out.println(sku.getSkuCode());
            System.out.println(price.getPrice());
            System.out.println(cartProduct.getQuantity());
            System.out.println("sub total " + price.getPrice()*Integer.parseInt(cartProduct.getQuantity()));
            viewCart.set(viewCart +"\n"+"product name : " + product.getProductName() + " price : " + price.getPrice() + " quantity : " + cartProduct.getQuantity() +
                    " sub total : " + price.getPrice()*Integer.parseInt(cartProduct.getQuantity()));
            total.updateAndGet(v -> v + price.getPrice() * Integer.parseInt(cartProduct.getQuantity()));
        });
        viewCart.set(viewCart+"\nTotal: "+total +"\n");
        return viewCart.toString();
    }

    
}