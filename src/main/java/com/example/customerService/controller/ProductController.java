package com.example.customerService.controller;

import com.example.customerService.entity.ProductsEntity;
import com.example.customerService.entity.SkuEntity;
import com.example.customerService.model.InventoryModel;
import com.example.customerService.model.PriceModel;
import com.example.customerService.model.ProductModel;
import com.example.customerService.model.SkuModel;
import com.example.customerService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //API - add products
    @PostMapping("/addProducts")
    public ResponseEntity<String> addProduct(@RequestBody ProductModel productModel){
        return productService.addProducts(productModel);
    }

    //API - to add skus
    @PatchMapping("/addSkus")
    public ProductsEntity addSkus(@RequestParam String productCode, @RequestBody SkuModel skuModel){
        return productService.addSkus(productCode, skuModel);
    }

    //API - to add price
    @PatchMapping("/addPrice")
    public SkuEntity addPrice(@RequestParam String skuCode, @RequestBody PriceModel priceModel){
        return productService.addPrice(skuCode, priceModel);
    }

    //API - to get all products
    @GetMapping("/getAllProducts")
    public List<ProductsEntity> getAllProducts(){
        return productService.getAllProducts();
    }

    //API - to add quantity available
    @PatchMapping("/addStock")
    public ResponseEntity<String> addStock(@RequestParam String skuCode, @RequestBody InventoryModel inventoryModel){
        return productService.addStock(skuCode, inventoryModel);
    }


}
