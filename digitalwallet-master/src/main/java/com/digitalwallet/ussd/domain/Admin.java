package com.digitalwallet.ussd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

    public Admin() {
    }

    public Admin(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String AdminUsername;
    private String AdminPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setadminUsername(String AdminUsername) {
        this.AdminUsername = AdminUsername;
    }

    public void setadminPassword(String AdminPassword) {
        this.AdminPassword = AdminPassword;
    }
}
