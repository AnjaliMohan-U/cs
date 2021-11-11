package com.example.customerService.repository;

import com.example.customerService.entity.ShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingEntity, Long> {
}
