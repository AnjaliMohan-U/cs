package com.example.customerService.service;

import com.example.customerService.entity.InventoryEntity;
import com.example.customerService.entity.PriceEntity;
import com.example.customerService.entity.ProductsEntity;
import com.example.customerService.entity.SkuEntity;
import com.example.customerService.model.InventoryModel;
import com.example.customerService.model.PriceModel;
import com.example.customerService.model.ProductModel;
import com.example.customerService.model.SkuModel;
import com.example.customerService.repository.ProductRepository;
import com.example.customerService.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkuRepository skuRepository;

    //----------------------------------------Add product details-------------------------------------------------//

    public ResponseEntity<String> addProducts(ProductModel productsModel){
        ProductsEntity productsEntity = new ProductsEntity();

        productsEntity.setProductCode(productsModel.getProductCode());
        productsEntity.setProductName(productsModel.getProductName());
        productsEntity.setDescription(productsModel.getDescription());

        productRepository.save(productsEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("product added");
    }
    //----------------------------------------Add Sku details to products--------------------------------------------//

    public ProductsEntity addSkus(String productCode, SkuModel skuModel){
        ProductsEntity product = productRepository.findByProductCode(productCode);
        if(product!=null){
            System.out.println("found the product:"+ skuModel.getSkuCode());
            List<SkuEntity> skuEntityList = new ArrayList<>();

            SkuEntity sku = new SkuEntity();
            sku.setSkuCode(skuModel.getSkuCode());
            sku.setSize(skuModel.getSize());
            sku.setProducts(product);

            skuEntityList.add(sku);
            product.setSkuEntityList(skuEntityList);
            return productRepository.save(product);
        }
        return null;
    }
    //----------------------------------------Add price details-------------------------------------------------//

    public SkuEntity addPrice(String skuCode, PriceModel priceModel){
        SkuEntity sku = skuRepository.findBySkuCode(skuCode);
        if(sku!=null){
            System.out.println("found sku :"+ priceModel.getPrice());
            PriceEntity price = new PriceEntity();
            price.setPrice(priceModel.getPrice());
            price.setCurrency(priceModel.getCurrency());
            price.setSkuEntity(sku);
            sku.setPriceEntity(price);
            return skuRepository.save(sku);
        }
        return null;
    }

    //--------------------------------------Entity to Model Conversion---------------------------------------------//

    private ProductModel getProductModel(ProductsEntity productsEntity){
        ProductModel productsModel = new ProductModel();
        List<SkuModel> skuModelList = new ArrayList<>();
        productsEntity.getSkuEntityList().forEach(x->{
            SkuModel skuModel = new SkuModel();
            skuModel.setSkuCode(x.getSkuCode());
            skuModel.setSize(x.getSize());

            PriceModel priceModel = new PriceModel();
            priceModel.setPrice(x.getPriceEntity().getPrice());
            priceModel.setCurrency(x.getPriceEntity().getCurrency());

            InventoryModel inventoryModel = new InventoryModel();
            inventoryModel.setQuantityAvailable(x.getInventoryEntity().getQuantityAvailable());
            skuModel.setPriceModel(priceModel);
            skuModel.setInventoryModel(inventoryModel);
        });
        productsModel.setProductCode(productsEntity.getProductCode());
        productsModel.setProductName(productsEntity.getProductName());
        productsModel.setDescription(productsEntity.getDescription());
        productsModel.setSkuModelList(skuModelList);
        return productsModel;
    }
    public List<ProductsEntity> getAllProducts(){
        return productRepository.findAll();
    }


    //----------------------------------------Inventory Service-------------------------------------------------//

    public ResponseEntity<String> addStock(String skuCode, InventoryModel inventoryModel) {
        Optional<SkuEntity> sku = skuRepository.findById(skuCode);
        if (sku.isPresent()) {
            InventoryEntity inventory = new InventoryEntity();
            inventory.setQuantityAvailable(inventoryModel.getQuantityAvailable());
            inventory.setSkuEntity(sku.get());
            sku.get().setInventoryEntity(inventory);
            skuRepository.save(sku.get());
            return ResponseEntity.status(HttpStatus.CREATED).body("Inventory added successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("sku not found!");
    }
}
