package com.example.customer.model;

public enum CustomerSexEnum {
    Null(null),
    Male("Male"),
    Female("Female"),
    Others("Other");

    private String sex;

    CustomerSexEnum(String customerSex) {this.sex = customerSex;}

    public String getSex(){ return sex;}
}

