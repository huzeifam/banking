package com.example.banking.model;

public class CustomerCreateRequest {

    private String passNr;
    private String gbDate;
    private String firstName;
    private String lastName;
    private String street;
    private String streetNo;
    private String ort;

    public CustomerCreateRequest(String passNr, String gbDate, String firstName, String lastName, String street, String streetNo, String ort) {
        this.passNr = passNr;
        this.gbDate = gbDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNo = streetNo;
        this.ort = ort;
    }

    public String getPassNr() {
        return passNr;
    }

    public String getGbDate() {
        return gbDate;
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

    public String getOrt() {
        return ort;
    }
}
