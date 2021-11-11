package com.example.customerService.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderEntity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private CustomerEntity customerEntity;
    @OneToOne(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private ShippingEntity shippingEntity;
    @Column
    private String orderStatus;

    public OrderEntity() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public ShippingEntity getShippingEntity() {
        return shippingEntity;
    }

    public void setShippingEntity(ShippingEntity shippingEntity) {
        this.shippingEntity = shippingEntity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
