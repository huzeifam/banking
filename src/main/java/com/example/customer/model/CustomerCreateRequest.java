package com.example.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.swing.*;
import java.time.LocalDate;



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
    private boolean hasOnlineBanking;
    private boolean investing;
    private boolean naturalPerson;
    private boolean hasAnotherBank;
    private boolean isSaving;
    private boolean isCreditWorthy;


    public CustomerCreateRequest(String idCardNo, LocalDate birthDate, String firstName, String lastName, String email, String telephone,
                                 String street, String streetNo, String zipCode, String city, String country, boolean hasOnlineBanking,
                                 boolean investing, boolean naturalPerson, boolean hasAnotherBank, boolean isSaving, boolean isCreditWorthy) {
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
        this.hasOnlineBanking = hasOnlineBanking;
        this.investing = investing;
        this.naturalPerson = naturalPerson;
        this.hasAnotherBank = hasAnotherBank;
        this.isSaving = isSaving;
        this.isCreditWorthy = isCreditWorthy;
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

    public boolean isHasOnlineBanking() {
        return hasOnlineBanking;
    }

    public boolean isInvesting() {
        return investing;
    }

    public boolean isNaturalPerson() {
        return naturalPerson;
    }

    public boolean isHasAnotherBank() {
        return hasAnotherBank;
    }

    public boolean isSaving() {
        return isSaving;
    }

    public boolean isCreditWorthy() {
        return isCreditWorthy;
    }
}
