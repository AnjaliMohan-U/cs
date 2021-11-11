package com.example.customerService.model;

public class OrderModel {
    private String orderStatus;
    private CustomerModel customerModel;
    private ShippingModel shippingModel;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public ShippingModel getShippingModel() {
        return shippingModel;
    }

    public void setShippingModel(ShippingModel shippingModel) {
        this.shippingModel = shippingModel;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", customerModel=" + customerModel +
                ", shippingModel=" + shippingModel +
                '}';
    }
}
