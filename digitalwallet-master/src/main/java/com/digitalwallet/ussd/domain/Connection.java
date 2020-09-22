package com.digitalwallet.ussd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Connection{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int connectionid;
    private int userid;
    private int paymentid;

    public int getConnectiond() {
        return connectionid;
    }

    public void setConnectionid(int connectionid) {
        this.connectionid = connectionid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid ){
        this.userid = userid;
    }

    public int getPaymentid(){
        return paymentid;
    }

    public void setPaymentid(int paymentid){
        this.paymentid = paymentid;
    }


}