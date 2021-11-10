package com.example.customerService.model;

//FirstName
//        LastName
//        Email - This can be used to login
//        Mobile Number
//        Address (should be able to add multiple addresses) -> (line 1, line2, postal code, state, city, shippingAddress(true/false), billingAddress(true/false)

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class CustomerModel {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @NotNull
    private Long mobileNumber;
    private String password;
    private List<AddressModel> addressModelList;
    private List<CartModel> cartModels;

    public List<AddressModel> getAddressModelList() {
        return addressModelList;
    }

    public void setAddressModelList(List<AddressModel> addressModelList) {
        this.addressModelList = addressModelList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<CartModel> getCartModels() {
        return cartModels;
    }

    public void setCartModels(List<CartModel> cartModels) {
        this.cartModels = cartModels;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", password='" + password + '\'' +
                ", addressModelList=" + addressModelList +
                ", cartModels=" + cartModels +
                '}';
    }
}
