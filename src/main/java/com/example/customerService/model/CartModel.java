package com.example.customerService.model;

//Input Fields - productCode, skyCode, quantity
public class CartModel {
    private String productCode;
    private String skuCode;
    private String quantity;
    private CustomerModel customerModel;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "productCode='" + productCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", quantity='" + quantity + '\'' +
                ", customerModel=" + customerModel +
                '}';
    }
}