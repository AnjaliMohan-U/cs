package com.example.customerService.repository;

import com.example.customerService.entity.PriceEntity;
import com.example.customerService.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    PriceEntity findBySkuEntity(SkuEntity sku);
}
