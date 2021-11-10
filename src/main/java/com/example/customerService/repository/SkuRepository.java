package com.example.customerService.repository;

import com.example.customerService.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<SkuEntity, String> {
    SkuEntity findBySkuCode(String skuCode);
}
