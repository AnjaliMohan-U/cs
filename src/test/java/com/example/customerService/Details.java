package com.example.customerService;

import com.example.customerService.entity.*;
import com.example.customerService.model.*;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Details {

    public static CustomerModel getCustomerModel(){
        CustomerModel customer = new CustomerModel();
        customer.setFirstName("Anjali Mohan");
        customer.setLastName("Uppu");
        customer.setEmail("anjali@gmail.com");

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("cjss_encryption"); // encryptor's private key
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        String data = "password";
        String encryptedData = encryptor.encrypt(data);
        customer.setPassword(encryptedData);
        customer.setMobileNumber(8978605045l);
        return customer;
    }

    public static AddressModel getAddressModel(){
        AddressModel addressModel = new AddressModel();
        addressModel.setLine1("spring valley");
        addressModel.setLine2("sairam colony");
        addressModel.setCity("vizag");
        addressModel.setState("andhra");
        addressModel.setPostalCode("530048");
        addressModel.setBillingAddress(true);
        addressModel.setShippingAddress(true);
        return addressModel;
    }

    public static CustomerEntity getCustomerEntity(){
        CustomerEntity customer = new CustomerEntity();
        CustomerModel c = getCustomerModel();
        customer.setFirstName(c.getFirstName());
        customer.setLastName(c.getLastName());
        customer.setEmail(c.getEmail());
        customer.setPassword(c.getPassword());
        customer.setMobileNumber(c.getMobileNumber());

        List<AddressEntity> addressEntities = new ArrayList<>();
        AddressModel a = getAddressModel();
        AddressEntity address = new AddressEntity();
        address.setLine1(a.getLine1());
        address.setLine2(a.getLine2());
        address.setCity(a.getCity());
        address.setState(a.getState());
        address.setPostalCode(a.getPostalCode());
        address.setBillingAddress(a.getBillingAddress());
        address.setShippingAddress(a.getShippingAddress());
        address.setCustomerEntity(customer);
        addressEntities.add(address);

        customer.setAddressEntityList(addressEntities);
        return customer;
    }

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
