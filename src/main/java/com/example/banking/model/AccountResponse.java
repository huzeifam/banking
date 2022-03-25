package com.example.banking.model;

import org.iban4j.Iban;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class AccountResponse {

    @Id
    private Integer aNr;
    private Integer kNr;
    private String iban;
    private Double balanceInEuro;
    private LocalDate startDate;


//    @ManyToOne
//    private CustomerResponse customerResponse;



    public AccountResponse(){

    }

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
    public Double getBalanceInEuro() { return Math.round(balanceInEuro*100.0)/100.0; }
    public LocalDate getStartDate() { return startDate; }

    public void setBalanceInEuro(Double balanceInEuro) {
        this.balanceInEuro = balanceInEuro;
    }
}
