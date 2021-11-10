package com.example.customerService.model;

public class InventoryModel {
    private String quantityAvailable;

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "InventoryModel{" +
                "quantityAvailable='" + quantityAvailable + '\'' +
                '}';
    }
}
