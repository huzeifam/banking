package com.example.banking.model;

import org.iban4j.Iban;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
public class AccountResponse {

    @Id
    private Integer aNr;
    private Integer kNr;
    private String iban;
    private Double balanceInEuro;
    private LocalDateTime startDate;





    public AccountResponse(){

    }

    public AccountResponse(Integer kNr, Integer aNr, String iban,
                           Double balanceInEuro, LocalDateTime startDate) {
        this.kNr = kNr;
        this.aNr = aNr;
        this.iban = iban;
        this.balanceInEuro = balanceInEuro;
        this.startDate = startDate;
    }

    public Integer getkNr() {return kNr; }
    public Integer getaNr() { return aNr; }
    public String getIban() { return iban; }
    public Double getBalanceInEuro() { return Math.round(balanceInEuro*100.0)/100.0; }
    public LocalDateTime getStartDate() { return startDate; }

}
