package com.example.customerService.repository;

import com.example.customerService.entity.InventoryEntity;
import com.example.customerService.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, String> {

    InventoryEntity findBySkuEntity(SkuEntity sku);
}
