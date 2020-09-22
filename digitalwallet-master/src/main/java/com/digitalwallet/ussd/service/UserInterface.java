package com.digitalwallet.ussd.service;

import java.util.*;
import java.io.*;
import java.util.List;

import com.digitalwallet.ussd.persistence.BankRepository;
import com.digitalwallet.ussd.persistence.UserRepository;
import com.digitalwallet.ussd.persistence.ConnectionRepository;
import com.digitalwallet.ussd.domain.Connection;
import com.digitalwallet.ussd.domain.Operator;
import com.digitalwallet.ussd.persistence.OperatorRepository;
import com.digitalwallet.ussd.domain.Bank;
import com.digitalwallet.ussd.domain.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UserInterface {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OperatorRepository operatorRespository;
    @Autowired
    private Services services;
    @Autowired
    private Insurance insurance;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ConnectionRepository connectionRepository;
    /*@Autowired
    private OperatorService Operator;*/


    public String findAll() {
        String output = "";
        List<Operator> operators = operatorRespository.findAll();
        for (com.digitalwallet.ussd.domain.Operator operator : operators) {
            output = output + operator.getId().toString() + " " + operator.getName() + "\n";
        }
        return output;
    }

    public String find(String pin){
        String output = "";
        boolean found1 = false;
        boolean found2 = false;
        boolean found3 = false;
        int current_pin = Integer.parseInt(pin);
        int UserID = 0;
        int Payment = 0;

        List <User> users = userRepository.findBypin(current_pin);
        for (com.digitalwallet.ussd.domain.User user : users) {
            found1 = true;
            UserID = user.getID();

        List<Connection> connections = connectionRepository.findByuserid(UserID);
        for (com.digitalwallet.ussd.domain.Connection connection : connections) {
            found2 = true;
            Payment = connection.getPaymentid();


        List<Bank> banks = bankRepository.findByidaccount(Payment);
        for (com.digitalwallet.ussd.domain.Bank bank : banks) {
                    found3= true;
                    output = output + bank.getBankid() + " " + bank.getBankname() + "\n";
        }}}


        if(found1==false){
            output = output+ "First loop failed \n";
        }

        else if(found2==false){
            output = output+"Second loop failed \n";
        }

        else if(found3==false){
            output = output+"Third loop failed \n";
        }
        output = output + "2 Mobile Money Account";

        return output;
    }

    public String display_menu(String pin) {
        return ( "Please select your financial service provider: \n" + find(pin) + "\n");
    }

    public String checkbank(String value){
        String output = "";
        int current_value=Integer.parseInt(value);
        List<Bank> banks = bankRepository.findBybankid(current_value);
        boolean found = false;
        for (com.digitalwallet.ussd.domain.Bank bank : banks) {
            found = true;
        }

        if(found == false){
            output = "Invalid Input.\n Your session has expired. \n Please restart the application.";
            return output;
        }
        else
            return getServices();

    }


    String getServices(){
        return ("Please select your payment service category: \n" +
                "1: Insurance\n2: Electricity\n3: Loan\n4: Refill Phone Plan\n");
    }

    String handleServiceSelection(String command, String[] commands) {
        switch (Integer.parseInt(command)) {
            case 1:
                return insurance.processCommand(command, commands);
            case 2:
                return "Electricity service page is currently under development ";
            case 3:
                return "Loan service page is currently under development ";
            case 4:
                return "Refill Phone Plan service page is currently under development ";

        }
        return "Invalid Input.\n Your session has expired. \n Please restart the application.";
    }

    String handleServiceCommand_1(String[] commands) {
        //find what service it is
        int service = Integer.parseInt(commands[2]);
        //1 is insurance
        //pass command to service command handler
        if (service == 1) {
            return insurance.processCommand(commands[commands.length - 1], commands);
        }
        return "UI Commands";
    }

    String processCommand(String[] commands, String command) {
        if (commands == null || commands.length <= 3) {
            return display_menu(commands[1]);
        }
        else{
            switch (commands.length) {
                case 4:
                    return checkbank(commands[3]);
                case 5:
                    return handleServiceSelection(commands[4], commands);
                default:
                    return handleServiceCommand_1(commands);
            }
        }

    }

}
