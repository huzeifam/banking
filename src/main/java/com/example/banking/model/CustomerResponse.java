package com.example.banking.model;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerResponse {

    @Id
    private Integer kNr;
    private String passNr;
    private String gbDate;
    private String vName;
    private String nName;
    private String straße;
    private String hNr;
    private String ort;

   /* @OneToMany(mappedBy = "customers", cascade = {
            CascadeType.ALL
    })
    AccountResponse accountResponse;*/

    public CustomerResponse(){

    }

    public CustomerResponse( Integer kNr,String passNr,
                            String gbDate, String vName,
                            String nName, String straße,
                            String hNr, String ort) {
        this.kNr = kNr;
        this.passNr = passNr;
        this.gbDate = gbDate;
        this.vName = vName;
        this.nName = nName;
        this.straße = straße;
        this.hNr = hNr;
        this.ort = ort;

    }

    public Integer getkNr() {
        return kNr;
    }

    public String getPassNr() {
        return passNr;
    }

    public String getGbDate() {
        return gbDate;
    }

    public String getvName() {
        return vName;
    }

    public String getnName() {
        return nName;
    }

    public String getStraße() {
        return straße;
    }

    public String gethNr() {
        return hNr;
    }

    public String getOrt() {
        return ort;
    }




    public void setPassNr(String passNr) {
        this.passNr = passNr;
    }

    public void setGbDate(String gbDate) {
        this.gbDate = gbDate;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public void sethNr(String hNr) {
        this.hNr = hNr;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
