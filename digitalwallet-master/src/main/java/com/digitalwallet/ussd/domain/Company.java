package com.digitalwallet.ussd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String category;
    private String name;
    private String policynumber;
    private String clientname;
    private double amount;
    private String coverage;
    private String carmodel;
    private String carbrand;
    private String year;

    private String api;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpolicynumber() {
        return policynumber;
    }

    public void setpolicynumber(String policynumber) {
        this.policynumber = policynumber;
    }

    public String getclientname() {
        return clientname;
    }

    public void setclientName(String clientname) {
        this.clientname = clientname;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getcarmodel(){return carmodel;}

    public void setcarmodel(String carmodel) {this.carmodel = carmodel;}

    public String getcarbrand(){return carbrand;}

    public void setcarbrand(String carbrand){this.carbrand = carbrand;}

    public String getyear(){return year;}

    public void setyear(String year){this.year = year;}

}
