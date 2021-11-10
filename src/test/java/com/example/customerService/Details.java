package com.example.customerService;

import com.example.customerService.entity.InventoryEntity;
import com.example.customerService.entity.PriceEntity;
import com.example.customerService.entity.ProductsEntity;
import com.example.customerService.entity.SkuEntity;
import com.example.customerService.model.InventoryModel;
import com.example.customerService.model.PriceModel;
import com.example.customerService.model.ProductModel;
import com.example.customerService.model.SkuModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Details {

    public static ProductModel getProductModel(){
        ProductModel prod = new ProductModel();
        prod.setProductCode("p1");
        prod.setProductName("Shirt");
        prod.setDescription("allen solly shirt");
        return prod;
    }

    public static SkuModel getSkuModel(){
        SkuModel sku = new SkuModel();
        sku.setSkuCode("s1");
        sku.setSize("M");
        return sku;
    }

    public static PriceModel getPriceModel(){
        PriceModel price = new PriceModel();
        price.setPrice(3000L);
        price.setCurrency("Rupees");
        return price;
    }

    public static InventoryModel getInventory(){
        InventoryModel inventory = new InventoryModel();
        inventory.setQuantityAvailable("300");
        return inventory;
    }

    public static ProductsEntity productsEntity(){
        ProductsEntity products = new ProductsEntity();
        ProductModel p = getProductModel();
        products.setProductCode(p.getProductCode());
        products.setProductName(p.getProductName());
        products.setDescription(p.getDescription());

        List<SkuEntity> skuEntities = new ArrayList<>();
        SkuEntity sku = new SkuEntity();
        SkuModel s = getSkuModel();
        sku.setSkuCode(s.getSkuCode());
        sku.setSize(s.getSize());
        sku.setProducts(products);

        PriceEntity price = new PriceEntity();
        PriceModel pr = getPriceModel();
        price.setPrice(pr.getPrice());
        price.setCurrency(pr.getCurrency());
        price.setSkuEntity(sku);

        InventoryEntity inventory = new InventoryEntity();
        InventoryModel i = getInventory();
        inventory.setQuantityAvailable(i.getQuantityAvailable());
        inventory.setSkuEntity(sku);
        sku.setInventoryEntity(inventory);
        sku.setProducts(products);
        sku.setPriceEntity(price);
        skuEntities.add(sku);
        products.setSkuEntityList(skuEntities);
        return products;

    }
}
