package com.example.customer.model;

public enum EmailTypeEnum {
    Private_Adress("private"),
    Business_Adress("business");


    private String emailType;

    EmailTypeEnum(String emailType) {
        this.emailType = emailType;
    }

    public String getEmailType() {
        return emailType;
    }
}
