package com.example.customer.model;

public enum AcademicTitleEnum {
    Null(null),
    NoTitle("-"),
    Doctor("Dr."),
    Professor("Prof."),
    Professor_Doctor("Prof. Dr.");


    private String academicTitle;

    AcademicTitleEnum(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }
}

