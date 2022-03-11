package com.example.banking.model;

import java.time.LocalDate;

public class AccountCreateRequest {

    private Integer kNr;
    private String iban;
    private Double balanceInEuro;
    private LocalDate startDate;

    public AccountCreateRequest(Integer kNr, String iban,
                                Double balanceInEuro, LocalDate startDate) {
        this.kNr = kNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }
    public Integer getkNr() { return kNr; }

    public String getIban() { return iban; }

    public Double getBalanceInEuro() { return balanceInEuro; }

    public LocalDate getStartDate() { return startDate; }


}
