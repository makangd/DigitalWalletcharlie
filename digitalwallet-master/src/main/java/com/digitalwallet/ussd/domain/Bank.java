package com.digitalwallet.ussd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private double amount;
    private int idaccount;
    private String bankname;
    private int bankid;

    public int getId() {
        return idaccount;
    }

    public void setId(int idaccount) {
        this.idaccount = idaccount;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname ){
        this.bankname = bankname;
    }

    public int getBankid(){
        return bankid;
    }

    public void setBankid(int bankid){
        this.bankid = bankid;
    }

    public double getAmount(){
        return amount;
    }

    public void setAMount(double amount){
        this.amount = amount;
    }
}