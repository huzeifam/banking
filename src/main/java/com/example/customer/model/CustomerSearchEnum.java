package com.example.customer.model;

public enum CustomerSearchEnum {
    FirstName("firstName"),
    LastName("lastName"),
    Gender("sex"),
    Street("street"),
    ZipCode("zipCode"),
    City("city"),
    Country("country");


    private String searchP;

    CustomerSearchEnum(String searchP) {
        this.searchP = searchP;
    }

    public String getSearchP() {
        return searchP;
    }
}

