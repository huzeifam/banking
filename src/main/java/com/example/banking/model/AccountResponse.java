package com.example.banking.model;

public class AccountResponse {

    private Integer aNr;
    private String iban;
    private Double balanceInEuro;
    private String startDate;

    public AccountResponse(Integer aNr, String iban,
                           Double balanceInEuro, String startDate) {
        this.aNr = aNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }

    public Integer getaNr() { return aNr; }
    public String getIban() { return iban; }
    public Double getBalanceInEuro() { return balanceInEuro; }
    public String getStartDate() { return startDate; }
}
