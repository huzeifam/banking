package com.example.banking.model;

import java.time.LocalDate;

public class AccountResponse {

    private Integer kNr;
    private Integer aNr;
    private String iban;
    private Double balanceInEuro;
    private LocalDate startDate;

    public AccountResponse(Integer kNr, Integer aNr, String iban,
                           Double balanceInEuro, LocalDate startDate) {
        this.kNr = kNr;
        this.aNr = aNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }

    public Integer getkNr() {return kNr; }
    public Integer getaNr() { return aNr; }
    public String getIban() { return iban; }
    public Double getBalanceInEuro() { return balanceInEuro; }
    public LocalDate getStartDate() { return startDate; }
}
