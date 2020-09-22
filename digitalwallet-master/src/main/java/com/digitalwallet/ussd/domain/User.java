package com.digitalwallet.ussd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int phonenumber;
    private int pin;


    public int getPhoneNumber(){
        return phonenumber;
    };

    public void setPhonenumber(int phonenumber){
        this.phonenumber = phonenumber;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id=id;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
