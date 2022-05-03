package com.example.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustomerCreateRequest {


    private String idCardNo;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String street;
    private String streetNo;
    private String zipCode;
    private String city;
    private String country;



    public CustomerCreateRequest(String idCardNo, LocalDate birthDate, String firstName, String lastName, String email, String telephone, String street, String streetNo, String zipCode, String city, String country) {
        this.idCardNo = idCardNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.street = street;
        this.streetNo = streetNo;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public LocalDate getBirthDate() {return birthDate;}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
