package com.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.util.List;

//Database Fields - productCode, productName, description
@Entity
@Table(name = "products")
public class ProductsEntity {
    @Id
    private String productCode;
    @Column
    private String productName;
    @Column
    private String description;
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<SkuEntity> skuEntityList;

    public ProductsEntity() {
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkuEntity> getSkuEntityList() {
        return skuEntityList;
    }

    public void setSkuEntityList(List<SkuEntity> skuEntityList) {
        this.skuEntityList = skuEntityList;
    }


}
