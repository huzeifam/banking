package com.example.banking.model;

public class AccountResponse {

    private Integer aNr;
    private String iban;
    private String balanceInEuro;
    private String startDate;

    public AccountResponse(Integer aNr, String iban,
                           String balanceInEuro, String startDate) {
        this.aNr = aNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }

    public Integer getaNr() { return aNr; }
    public String getIban() { return iban; }
    public String getBalanceInEuro() { return balanceInEuro; }
    public String getStartDate() { return startDate; }
}
