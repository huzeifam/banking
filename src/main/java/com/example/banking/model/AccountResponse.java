package com.example.banking.model;

import java.time.LocalDate;

public class AccountResponse {

    private Integer kNr;
    private Integer aNr;
    private String iban;
    private String balanceInEuro;
    private LocalDate startDate;

    public AccountResponse(Integer kNr, Integer aNr, String iban,
                           String balanceInEuro, LocalDate startDate) {
        this.kNr = kNr;
        this.aNr = aNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }

    public Integer getkNr() {return kNr; }
    public Integer getaNr() { return aNr; }
    public String getIban() { return iban; }
    public String getBalanceInEuro() { return balanceInEuro; }
    public LocalDate getStartDate() { return startDate; }
}
