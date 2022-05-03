package com.example.customer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "customers")
public class CustomerResponse {

    @Id
    private Integer customerNo;
    private String idCardNo;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String telephone;
    private String street;
    private String streetNo;
    private String zipCode;
    private String city;
    private String country;
    @JsonIgnore
    private String parameter;
    @JsonIgnore
    private String word;
    @JsonIgnore
    private AbstractButton jCheckbox;
    private boolean naturalPerson = jCheckbox.isSelected();



//    @OneToMany(mappedBy = "kNr", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountResponse> accountResponse;

    public CustomerResponse(){

    }

    public CustomerResponse(Integer customerNo, String idCardNo, LocalDate birthDate, String firstName,
                            String lastName, String sex, String email, String telephone, String street,
                            String streetNo, String zipCode, String city, String country, boolean naturalPerson) {
        this.customerNo = customerNo;
        this.idCardNo = idCardNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.telephone = telephone;
        this.street = street;
        this.streetNo = streetNo;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.naturalPerson = naturalPerson;
    }

    public Integer getCustomerNo() {
        return customerNo;
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

    public String getSex() {
        return sex;
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

    public boolean isNaturalPerson() {
        return naturalPerson;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setNaturalPerson(boolean naturalPerson) {
        this.naturalPerson = naturalPerson;
    }
}
