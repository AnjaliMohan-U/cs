package com.example.customerService.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @Column
    private String quantityAvailable;
    @OneToOne
    @JsonIgnoreProperties("inventoryEntity")

    private SkuEntity skuEntity;

    public InventoryEntity() {
    }

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public SkuEntity getSkuEntity() {
        return skuEntity;
    }

    public void setSkuEntity(SkuEntity skuEntity) {
        this.skuEntity = skuEntity;
    }
}
