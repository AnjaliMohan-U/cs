package com.example.customerService.service;

import com.example.customerService.entity.AddressEntity;
import com.example.customerService.entity.CustomerEntity;
import com.example.customerService.model.AddressModel;
import com.example.customerService.model.CustomerModel;
import com.example.customerService.model.LoginModel;
import com.example.customerService.repository.AddressRepository;
import com.example.customerService.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    //----------------------------------------register customer-------------------------------------------------//

    public ResponseEntity<String> register(CustomerModel customer) {
        CustomerEntity c = customerRepository.findByEmail(customer.getEmail());
        if(c==null){
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setFirstName(customer.getFirstName());
            customerEntity.setLastName(customer.getLastName());
            customerEntity.setEmail(customer.getEmail());
            customerEntity.setMobileNumber(customer.getMobileNumber());
            List<AddressEntity> addressEntityList = new ArrayList<>();
            customerEntity.setAddressEntityList(addressEntityList);


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

            String data = customer.getPassword();
            String encryptedData = encryptor.encrypt(data);
            customerEntity.setPassword(encryptedData);
            customerRepository.save(customerEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registration successful!");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("user already exists");
    }

    //----------------------------------------Entity to model conversion----------------------------------------------//

    private CustomerModel getCustomerModel(CustomerEntity customerEntity){
        CustomerModel customerModel = new CustomerModel();
        List<AddressModel> addressModelList = new ArrayList<>();
        customerEntity.getAddressEntityList().forEach(x->{
            AddressModel addressModel = new AddressModel();
            addressModel.setLine1(x.getLine1());
            addressModel.setLine2(x.getLine2());
            addressModel.setPostalCode(x.getPostalCode());
            addressModel.setState(x.getState());
            addressModel.setCity(x.getCity());
            addressModel.setShippingAddress(x.getShippingAddress());
            addressModel.setBillingAddress(x.getBillingAddress());
            addressModelList.add(addressModel);
        });
        customerModel.setFirstName(customerEntity.getFirstName());
        customerModel.setLastName(customerEntity.getLastName());
        customerModel.setEmail(customerEntity.getEmail());
        customerModel.setMobileNumber(customerEntity.getMobileNumber());
        customerModel.setPassword(customerEntity.getPassword());
        customerModel.setAddressModelList(addressModelList);
        return customerModel;
    }

    //----------------------------------------add address to customer-------------------------------------------------//

    public CustomerEntity addCustomerAddress(String email, AddressModel address) {
        Optional<CustomerEntity> c1 = customerRepository.findById(email);
        if(c1.isPresent()){
            System.out.println("entered if :"+ address.getCity());
            List<AddressEntity> addressEntityList = new ArrayList<>();
            AddressEntity a1 = new AddressEntity();
            a1.setLine1(address.getLine1());
            a1.setLine2(address.getLine2());
            a1.setState(address.getState());
            a1.setCity(address.getCity());
            a1.setPostalCode(address.getPostalCode());
            a1.setBillingAddress(address.getBillingAddress());
            a1.setShippingAddress(address.getShippingAddress());
            a1.setCustomerEntity(c1.get());
            addressEntityList.add(a1);
            c1.get().setAddressEntityList(addressEntityList);
            System.out.println("HI");
            return customerRepository.save(c1.get());
        }
        System.out.println("out without executing");
        return null;
    }

    //----------------------------------------get all the customer details -------------------------------------------//

    public List<CustomerEntity> getAllData(){
        return customerRepository.findAll();
    }
    public CustomerEntity getCustomerById(String email) {
        Optional<CustomerEntity> c = customerRepository.findById(email);
        return  c.get();
    }

    //----------------------------------------Login customer-------------------------------------------------//

    public ResponseEntity<String> loginCustomer(LoginModel loginModel) {
        String email= loginModel.getEmail();
        String password = loginModel.getPassword();
        CustomerEntity c = customerRepository.findByEmail(email);
        System.out.println(c);
        if(c!=null){
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
            String decrypted = encryptor.decrypt(c.getPassword());
            long EXPIRATIONTIME = 1000 * 60 * 60 * 24 * 1;
            String secret = "CJSSTECHNOLOGIES";
            if(email.equals(c.getEmail()) && password.equals(decrypted)){
                String JWT = Jwts.builder()
                        .setSubject(email)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                        .signWith(SignatureAlgorithm.HS512, secret)
                        .compact();

                String username = Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(JWT)
                        .getBody()
                        .getSubject();

                Date expirationDate= Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(JWT)
                        .getBody()
                        .getExpiration();
                System.out.println();
                return ResponseEntity.status(HttpStatus.CREATED).body("Jwt token :"+JWT);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not done.");
    }



}

