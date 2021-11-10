package com.example.customerService.service;

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

    }


}
