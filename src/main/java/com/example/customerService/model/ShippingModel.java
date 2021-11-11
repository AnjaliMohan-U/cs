package com.example.customerService.model;

import com.example.customerService.entity.OrderEntity;

public class ShippingModel {
    private String line1;
    private String line2;
    private String postalCode;
    private String state;
    private String city;
    private OrderEntity orderEntity;

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

    @Override
    public String toString() {
        return "ShippingModel{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", orderEntity=" + orderEntity +
                '}';
    }
}
