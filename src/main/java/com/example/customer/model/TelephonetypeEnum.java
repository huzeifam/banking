package com.example.customer.model;

public enum TelephonetypeEnum {
    Null(null),
    BusinessPhone("business"),
    PrivatePhone("private"),
    MobilePhone("mobile");


    private String telephoneNumberType;

    TelephonetypeEnum(String telephoneNumberType) {
        this.telephoneNumberType = telephoneNumberType;
    }

    public String getTelephoneNumberType() {
        return telephoneNumberType;
    }
}
