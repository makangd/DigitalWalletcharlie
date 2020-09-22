package com.digitalwallet.ussd.service;

import java.util.*;
import java.io.*;
import java.util.List;

import com.digitalwallet.ussd.domain.Company;
import com.digitalwallet.ussd.persistence.UserRepository;
import com.digitalwallet.ussd.domain.User;

import com.digitalwallet.ussd.persistence.BankRepository;
import com.digitalwallet.ussd.domain.Bank;

import com.digitalwallet.ussd.persistence.ConnectionRepository;
import com.digitalwallet.ussd.domain.Connection;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Registration {

    @Autowired
    private CreateAccount createaccount;

    @Autowired
    private PaymentConnection paymentconnection;

    @Autowired
    private MobileConnection mobileconnection;

    private Login login;

        private static final Random RANDOM = new Random();
        String Registration_Menu(){
            return ("Welcome to the Registration\n" +
                    "Enter one of the following option(s): \n" +
                    "1. Create Digital Wallet Account \n" +
                    "2. Connect a Bank Account\n" +
                    "3. Create Mobile Money Account \n" +
                    "4. Exit \n");
        }


    String handleLoginSelection(String command, String[] commands) {
        switch (Integer.parseInt(command)) {
            case 1:
                return createaccount.process(commands, command);
            case 2:
                return paymentconnection.process(commands, command);
            case 3:
                return mobileconnection.process(commands, command);
            case 4:
                return "Thank you for using Digital Wallet Application";

        }
        return "Invalid input \n Your session has expired. \n Please restart the application.";
    }


    String handleServiceCommand(String[] commands) {
        //find what service it is
        int service = Integer.parseInt(commands[1]);
        //1 is insurance
        //pass command to service command handler
        if (service == 1) {
            return createaccount.process(commands, commands[commands.length-1 ]);
        }
        else if(service == 2)
            return paymentconnection.process(commands, commands[commands.length-1 ]);

        else if(service ==3)
            return mobileconnection.process(commands, commands[commands.length-1 ]);
        return "Login Commands: "+commands;
    }


        public String process(String[] commands, String command) {
            if (commands == null || commands.length <= 1) {
                return Registration_Menu();
            }
            else{
                switch (commands.length){
                    case 2:
                        return handleLoginSelection(commands[1], commands);
                    default:
                        return handleServiceCommand(commands);
                }
            }

        }
}

