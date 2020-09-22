package com.digitalwallet.ussd.service;

import java.util.*;
import java.io.*;
import java.util.Optional;
import java.util.List;

import com.digitalwallet.ussd.domain.Operator;
import com.digitalwallet.ussd.persistence.CompanyRepository;
import com.digitalwallet.ussd.domain.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class Insurance {
    @Autowired
    private CompanyRepository companyrepository;


    public String findAll() {
        String output = "";
        List<Company> companys = companyrepository.findAll();
        for (com.digitalwallet.ussd.domain.Company company : companys) {
            output = output + company.getId().toString() + " " + company.getName() + "\n";
        }
        return output;
    }

    public String readcompany(String id){
        String output = "";
        long current_id =Long.parseLong(id);
        List<Company> companys = companyrepository.findByid(current_id);
        boolean selected = false;
        for (com.digitalwallet.ussd.domain.Company company : companys) {
            selected = true;
        }

        if(selected == false){
            output = "Invalid input. \nPlease enter the correct policy number.";
            return output;
        }
        else
            return requestPolicyNumber();

    }


    public String findBypolicynumber(String policynumber){

        String output = "";
        Boolean found = false;
        List <Company> companys = companyrepository.findBypolicynumber(policynumber);
        for (com.digitalwallet.ussd.domain.Company company : companys) {
                found = true;
                output = output + "Company Name: " + company.getName() + "\n     Client Name: " + company.getclientname() + "\n     Policy Number: " +
                        company.getpolicynumber() + "\n     Car Brand: " + company.getcarbrand() + "\n     Car Model: " + company.getcarmodel() + "\n     Year: "
                        + company.getyear() + "\n     Amount Due: " + company.getAmount() + "\nPress 1 to pay the amount";

        }
        if(found == false)
            output = output+ "The policy number you have entered is incorrect";
        return output;
    }

    String getInsuranceCompanies() {
        return ("Please select Insurance company: \n" + findAll() + "2 American Family \n3 State Farm\n4 Geico \n5 Progressive");
    }

    String requestPolicyNumber() {
        return ("Please enter your policy number \n");
    }

    String findPolicy(String policyNumber) {
        return (
                findBypolicynumber (policyNumber)
        );
    }

    String enteramount(){
        return ("Please enter an amount to pay: \n");
    }

    String processedpayment(String policynumber, String amount){
        String output = "";
        double newamount=Double.parseDouble(amount);
        List <Company> companys = companyrepository.findBypolicynumber(policynumber);
        for (com.digitalwallet.ussd.domain.Company company : companys) {
            if (company.getAmount() < newamount){
                output = output +"The amount cannot be processed";
            }

            else{
                double currentamount = company.getAmount() - newamount;
                company.setAmount(currentamount);
                companyrepository.save(company);
                output = output +"Congratulations, your payment has been successful! \n     Amount Paid: $"+ newamount + "\n     Remaining Balance: $" +company.getAmount() +
                        "\n\nThank you for using our Digital Wallet \n";
            }

        }
        return output;
    }

    String handleMenuSelection(String command, String[] commands) {
        switch (Integer.parseInt(command)) {
            case 1:
                return enteramount();
        }
        return "Invalid Entry.";
    }

    String handlePayingSelection(String command, String[] commands) {
        switch (Integer.parseInt(command)) {
            case 1:
                return enteramount();
        }
        return "Invalid Entry.";
    }

    String processCommand(String command, String[] commands) {
        String policynumber = "";
        if ( commands.length <= 5) {
            return getInsuranceCompanies();
        } else {
            switch (commands.length) {
                case 6:
                    return readcompany(commands[5]);
                case 7:
                    return findPolicy(commands[6]);
                case 8:
                    return handlePayingSelection(commands[7], commands);
                case 9:
                    return processedpayment(commands[6], commands[8]);
            }

        }
        return null;
    }
}
