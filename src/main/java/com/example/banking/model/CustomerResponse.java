package com.example.banking.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerResponse {

    @Id
    private Integer customerNo;
    private String idCardNo;
    private String birthDate;
    private String firstName;
    private String lastName;
    private String street;
    private String streetNo;
    private String city;

//    @OneToMany(mappedBy = "kNr", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountResponse> accountResponse;

    public CustomerResponse(){

    }

    public CustomerResponse( Integer customerNo,String idCardNo,
                            String birthDate, String firstName,
                            String lastName, String street,
                            String streetNo, String city) {
        this.customerNo = customerNo;
        this.idCardNo = idCardNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.streetNo = streetNo;
        this.city = city;

    }

    public Integer getCustomerNo() {
        return customerNo;
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




    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public void setCity(String city) {
        this.city = city;
    }
}
