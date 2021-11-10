package com.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//Database Fields - skuCode, price, currency
@Entity
@Table(name = "prices")
public class PriceEntity {
    @Id
    @Column
    private Long price;
    @Column
    private String currency;
    @OneToOne
    @JsonIgnoreProperties("priceEntity")
    private SkuEntity skuEntity;

    public PriceEntity() {
    }

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

    public SkuEntity getSkuEntity() {
        return skuEntity;
    }

    public void setSkuEntity(SkuEntity skuEntity) {
        this.skuEntity = skuEntity;
    }


}