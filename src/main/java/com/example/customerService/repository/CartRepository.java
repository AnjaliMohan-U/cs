package com.example.customerService.repository;

import com.example.customerService.entity.CartEntity;
import com.example.customerService.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerEntity(CustomerEntity Email);

}
