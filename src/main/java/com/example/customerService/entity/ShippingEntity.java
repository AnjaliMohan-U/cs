package com.example.customerService.entity;

import javax.persistence.*;

@Entity
@Table(name = "shippingDetails")
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(name = "line1")
    private String line1;
    @Column(name = "line2")
    private String line2;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderEntity orderEntity;

    public ShippingEntity() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
