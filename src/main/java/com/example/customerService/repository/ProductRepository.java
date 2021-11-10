package com.example.customerService.repository;

import com.example.customerService.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, String> {
    ProductsEntity findByProductCode(String productCode);
}
