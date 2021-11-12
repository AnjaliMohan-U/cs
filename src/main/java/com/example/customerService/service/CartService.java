package com.example.customerService.service;

import com.example.customerService.entity.*;
import com.example.customerService.model.*;
import com.example.customerService.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
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
    @Autowired
    private OrderRepository orderRepository;

    //----------------------------------------Add product to cart-------------------------------------------------//

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

    //----------------------------------------view cart -------------------------------------------------//

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
            viewCart.set(viewCart +"\n"+"product name : " + product.getProductName() + " price : " + price.getPrice() + "quantity : " + cartProduct.getQuantity() +
                    " sub total : " + price.getPrice()*Integer.parseInt(cartProduct.getQuantity()));
            total.updateAndGet(v -> v + price.getPrice() * Integer.parseInt(cartProduct.getQuantity()));
        });
        viewCart.set(viewCart+"\nTotal: "+total +"\n");
        return viewCart.toString();
    }

    //----------------------------------------Place order-------------------------------------------------//

    public ResponseEntity<String> placeOrder(String email, Long addressId) {
        CustomerEntity customer = customerRepository.findByEmail(email);
        List<CartEntity> cartEntities = cartRepository.findByCustomerEntity(customer);
        List<OrderEntity> orderEntities = new ArrayList<>();

        OrderModel order = new OrderModel();
        AtomicBoolean inventoryAvailable= new AtomicBoolean(true);
        cartEntities.stream().forEach(product -> {
            SkuEntity sku = skuRepository.findBySkuCode(product.getSkuCode());
            InventoryEntity inventoryEntity = inventoryRepository.findBySkuEntity(sku);
            if (Integer.parseInt(inventoryEntity.getQuantityAvailable()) >= Integer.parseInt(product.getQuantity())) {
                int available = Integer.parseInt(inventoryEntity.getQuantityAvailable())-Integer.parseInt(product.getQuantity());
                System.out.println(available);
                inventoryEntity.setQuantityAvailable(String.valueOf(available));
                sku.setInventoryEntity(inventoryEntity);
                skuRepository.save(sku);
                System.out.println(inventoryEntity.getQuantityAvailable());
            }
        });
        Optional<AddressEntity> address = customer.getAddressEntityList().stream().filter(a -> a.getAddressId().equals(addressId)).findFirst();
        ShippingModel shippingModel = new ShippingModel();
        shippingModel.setLine1(address.get().getLine1());
        shippingModel.setLine2(address.get().getLine2());
        shippingModel.setCity(address.get().getCity());
        shippingModel.setState(address.get().getState());
        shippingModel.setPostalCode(address.get().getPostalCode());

        order.setOrderStatus("order received");
        order.setShippingModel(shippingModel);
        CustomerModel c = getCustomerModel(customer);
        order.setCustomerModel(c);
        OrderEntity orderEntity = getOrderEntity(order);
        orderEntity.setCustomerEntity(customer);
        orderEntities.add(orderEntity);
        customer.setOrderEntities(orderEntities);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("order placed" +"\n" + "order status : " + order.getOrderStatus());
    }

    //----------------------------------------conversions-------------------------------------------------//

    private CustomerModel getCustomerModel(CustomerEntity customerEntity){
        CustomerModel customerModel = new CustomerModel();
        List<AddressModel> addressModelList = new ArrayList<>();
        customerEntity.getAddressEntityList().forEach(x->{
            AddressModel addressModel = new AddressModel();
            addressModel.setLine1(x.getLine1());
            addressModel.setLine2(x.getLine2());
            addressModel.setPostalCode(x.getPostalCode());
            addressModel.setState(x.getState());
            addressModel.setCity(x.getCity());
            addressModel.setShippingAddress(x.getShippingAddress());
            addressModel.setBillingAddress(x.getBillingAddress());
            addressModelList.add(addressModel);
        });
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setMobileNumber(customerEntity.getMobileNumber());
        customerModel.setPassword(customerEntity.getPassword());
        customerModel.setAddressModelList(addressModelList);
        return customerModel;
    }

    private OrderEntity getOrderEntity(OrderModel orderModel){
        OrderEntity orderEntity = new OrderEntity();

        ShippingEntity shippingEntity = new ShippingEntity();
        shippingEntity.setLine1(orderModel.getShippingModel().getLine1());
        shippingEntity.setLine2(orderModel.getShippingModel().getLine2());
        shippingEntity.setCity(orderModel.getShippingModel().getCity());
        shippingEntity.setState(orderModel.getShippingModel().getState());
        shippingEntity.setPostalCode(orderModel.getShippingModel().getPostalCode());

        orderEntity.setOrderStatus(orderModel.getOrderStatus());
        orderEntity.setShippingEntity(shippingEntity);
        shippingEntity.setOrderEntity(orderEntity);
        return orderEntity;
    }
}
