package com.example.customerService.model;

public class PriceModel {
    private Long price;
    private String currency;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "PriceModel{" +
                "price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
