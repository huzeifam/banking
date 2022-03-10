package com.example.banking.model;

public class AccountCreateRequest {


    private String iban;
    private Double balanceInEuro;
    private String startDate;

    public AccountCreateRequest(String iban,
                           Double balanceInEuro, String startDate) {

        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }


    public String getIban() { return iban; }

    public Double getBalanceInEuro() { return balanceInEuro; }

    public String getStartDate() { return startDate; }
}
