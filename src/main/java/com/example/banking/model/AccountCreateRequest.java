package com.example.banking.model;

import java.time.LocalDateTime;

public class AccountCreateRequest {

    private Integer kNr;
    private String iban;
    private Double balanceInEuro;
    private LocalDateTime startDate;

    public AccountCreateRequest(Integer kNr, String iban,
                                Double balanceInEuro, LocalDateTime startDate) {
        this.kNr = kNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }
    public Integer getkNr() { return kNr; }

    public String getIban() { return iban; }

    public Double getBalanceInEuro() { return balanceInEuro; }

    public LocalDateTime getStartDate() { return startDate; }


}
