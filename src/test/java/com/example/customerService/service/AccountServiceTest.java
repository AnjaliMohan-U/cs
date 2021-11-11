package com.example.customerService.service;

import com.example.customerService.Details;
import com.example.customerService.entity.CustomerEntity;
import com.example.customerService.model.AddressModel;
import com.example.customerService.model.CustomerModel;
import com.example.customerService.repository.AddressRepository;
import com.example.customerService.repository.CustomerRepository;
import org.hibernate.validator.constraints.ru.INN;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    AddressRepository addressRepository;
    @InjectMocks
    AccountService accountService;

    @Test
    public void checkRegistration(){
        CustomerModel customer = Details.getCustomerModel();
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!"),accountService.register(customer));
    }

    @Test
    public void checkAddress(){
        AddressModel addressModel = Details.getAddressModel();
        System.out.println(addressModel.getLine1());
        when(customerRepository.findByEmail("anjali@gmail.com")).thenReturn(Details.getCustomerEntity());
        when(customerRepository.save(customerRepository.findByEmail("anjali@gmail.com"))).thenReturn(Details.getCustomerEntity());
        System.out.println(Details.getCustomerEntity().getFirstName());
        System.out.println(accountService.addCustomerAddress("anjali@gmail.com",addressModel).getFirstName());
        assertEquals(Details.getCustomerEntity().getFirstName(), accountService.addCustomerAddress("anjali@gmail.com",addressModel).getFirstName());

    }

}
//    @Test
//    public void checkRegistration(){
//        boolean present = Details.getCustomerEntity().getEmail().equals("anjali@gmail.com");
//        CustomerModel customer = Details.getCustomerModel();
//        if(present){
//            assertEquals(ResponseEntity.status(HttpStatus.CONFLICT).body("user already exists"),accountService.register(customer));
//        }
//        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!"),accountService.register(customer));
//    }