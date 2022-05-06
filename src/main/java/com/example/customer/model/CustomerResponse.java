package com.example.customer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class CustomerResponse {

    @Id
    private Integer customerNo;
    private String idCardNo;
    private String nationality;
    private LocalDate birthDate;
    private String academicTitle;
    private String firstName;
    private String lastName;
    private String sex;
    private String email;
    private String emailType;
    private String telephone;
    private String telephoneNumberType;
    private String street;
    private String streetNo;
    private String zipCode;
    private String city;
    private String country;
    private boolean hasOnlineBanking;
    private boolean investing;
    private boolean naturalPerson;
    private boolean hasAnotherBank;
    private boolean saving;
    private boolean creditWorthy;
    @JsonIgnore
    private String word;



//    @OneToMany(mappedBy = "kNr", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<AccountResponse> accountResponse;

    public CustomerResponse(){

    }

    public CustomerResponse(Integer customerNo, String idCardNo, String nationality, LocalDate birthDate, String academicTitle, String firstName, String lastName,
                            String sex, String email, String emailType, String telephone, String telephoneNumberType, String street, String streetNo, String zipCode,
                            String city, String country, boolean hasOnlineBanking, boolean investing,
                            boolean naturalPerson, boolean hasAnotherBank, boolean saving, boolean creditWorthy) {
        this.customerNo = customerNo;
        this.idCardNo = idCardNo;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.academicTitle = academicTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.emailType = emailType;
        this.telephone = telephone;
        this.telephoneNumberType = telephoneNumberType;
        this.street = street;
        this.streetNo = streetNo;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.hasOnlineBanking = hasOnlineBanking;
        this.investing = investing;
        this.naturalPerson = naturalPerson;
        this.hasAnotherBank = hasAnotherBank;
        this.saving = saving;
        this.creditWorthy = creditWorthy;
    }

    public Integer getCustomerNo() {
        return customerNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

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

    public String getEmailType() {
        return emailType;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getTelephoneNumberType() {
        return telephoneNumberType;
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
        return saving;
    }

    public boolean isCreditWorthy() {
        return creditWorthy;
    }




    public void setCustomerNo(Integer customerNo) {
        this.customerNo = customerNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
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

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setTelephoneNumberType(String telephoneNumberType) {
        this.telephoneNumberType = telephoneNumberType;
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

    public void setHasOnlineBanking(boolean hasOnlineBanking) {
        this.hasOnlineBanking = hasOnlineBanking;
    }

    public void setInvesting(boolean investing) {
        this.investing = investing;
    }

    public void setNaturalPerson(boolean naturalPerson) {
        this.naturalPerson = naturalPerson;
    }

    public void setHasAnotherBank(boolean hasAnotherBank) {
        this.hasAnotherBank = hasAnotherBank;
    }

    public void setSaving(boolean saving) {
        saving = saving;
    }

    public void setCreditWorthy(boolean creditWorthy) {
        creditWorthy = creditWorthy;
    }
}
