package com.example.banking.model;

public class CustomerCreateRequest {

    private String idCardNo;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String street;
    private String streetNo;
    private String city;

    public CustomerCreateRequest(String idCardNo, String birthDate, String firstName, String lastName, String street, String streetNo, String city) {
        this.idCardNo = idCardNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNo = streetNo;
        this.city = city;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getCity() {
        return city;
    }
}
