package com.example.customerService.model;

import java.util.List;
import javax.validation.constraints.*;

//Database Fields - productCode, productName, description
public class ProductModel {
    @NotNull
    private String productCode;
    @NotBlank
    private String productName;
    private String description;
    private List<SkuModel> skuModelList;

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

    public List<SkuModel> getSkuModelList() {
        return skuModelList;
    }

    public void setSkuModelList(List<SkuModel> skuModelList) {
        this.skuModelList = skuModelList;
    }

    @Override
    public String toString() {
        return "ProductsModel{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", skuModelList=" + skuModelList +
                '}';
    }
}
