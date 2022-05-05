package com.example.customer.model;

public enum CustomerSearchEnum {
    LastName("lastName"),
    FirstName("firstName"),
//    Male("sex-male"),
//    Female("sex-female"),
//    Others("sex-others"),
    IdCardNumber("idCardNo"),
    City("city"),
    Country("country");


    private String parameter;

    CustomerSearchEnum(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }


}

