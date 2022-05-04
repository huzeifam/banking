package com.example.customer.model;

public enum CustomerSearchEnum {
    LastName("lastName"),
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

