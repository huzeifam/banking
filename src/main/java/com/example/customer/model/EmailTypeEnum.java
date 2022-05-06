package com.example.customer.model;

public enum EmailTypeEnum {
    Null(null),
    PrivateAdress("private"),
    BusinessAdress("business");


    private String emailType;

    EmailTypeEnum(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailType() {
        return emailType;
    }
}
