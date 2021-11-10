package com.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//Database Fields - skuCode, productCode, size
@Entity
@Table(name = "skus")
public class SkuEntity {
    @Id
    private String skuCode;
    @Column
    private String size;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productCode")
    @JsonIgnoreProperties("skuEntityList")
    private ProductsEntity products;
    @OneToOne(mappedBy = "skuEntity", cascade=CascadeType.ALL)
    private PriceEntity priceEntity;
    @OneToOne(mappedBy = "skuEntity", cascade=CascadeType.ALL)
    @JsonIgnoreProperties("skuEntity")
    private InventoryEntity inventoryEntity;

    public SkuEntity() {
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductsEntity getProducts() {
        return products;
    }

    public void setProducts(ProductsEntity products) {
        this.products = products;
    }

    public PriceEntity getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(PriceEntity priceEntity) {
        this.priceEntity = priceEntity;
    }

    public InventoryEntity getInventoryEntity() {
        return inventoryEntity;
    }

    public void setInventoryEntity(InventoryEntity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
    }

}
