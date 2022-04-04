package com.example.banking.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class CustomerResponse {

    @Id
    private Integer customerNo;
    private String passNr;
    private String gbDate;
    private String firstName;
    private String lastName;
    private String street;
    private String streetNo;
    private String ort;

//    @OneToMany(mappedBy = "kNr", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountResponse> accountResponse;

    public CustomerResponse(){

    }

    public CustomerResponse( Integer customerNo,String passNr,
                            String gbDate, String firstName,
                            String lastName, String street,
                            String streetNo, String ort) {
        this.customerNo = customerNo;
        this.passNr = passNr;
        this.gbDate = gbDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNo = streetNo;
        this.ort = ort;

    }

    public Integer getCustomerNo() {
        return customerNo;
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




    public void setPassNr(String passNr) {
        this.passNr = passNr;
    }

    public void setGbDate(String gbDate) {
        this.gbDate = gbDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
