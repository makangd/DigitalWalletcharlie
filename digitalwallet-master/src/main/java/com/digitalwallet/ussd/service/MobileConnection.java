package com.digitalwallet.ussd.service;

import com.digitalwallet.ussd.domain.Bank;
import com.digitalwallet.ussd.domain.Connection;
import com.digitalwallet.ussd.domain.User;
import com.digitalwallet.ussd.persistence.BankRepository;
import com.digitalwallet.ussd.persistence.ConnectionRepository;
import com.digitalwallet.ussd.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MobileConnection {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    private Login login;
    private static final Random RANDOM = new Random();

    public String enterID(){
        return "Enter your SSN to continue\n";
    }

    public String findbyid(String id){
        String output = "";
        int current_id= Integer.parseInt(id);
        boolean found = false;

        List <User> users = userRepository.findByid(current_id);
        for (User user : users){
            output = output + "Phone number: "+user.getPhoneNumber() + "\n" +
                    "SSN: "+ user.getID() + "\n" +
                    "Please enter 1 to proceed.";
            found =true;
        }

        if(found == false){
            output ="SSN is incorrect \n" +
                    "Your session has expired. \n Please restart the application \n";
        }
        return output;
    }


        String complete(){
            return("Congratulation, your Mobile Money Account has been successfully created.\n" +
                    "Thank your for using the Digital Wallet.");
        }


    String set_payment_account(String id, String account){

        String output = "";
        int current_id = Integer.parseInt(id);
        boolean found = false;
        boolean found1 = false;
        int current_account = Integer.parseInt(account);
        int min = 1;
        int max = 9;
        int connection_id;
        String current_connection;

        List <User> users = userRepository.findByid(current_id);
        for (User user : users) {
            output=output + "User ID: " + user.getID() +"\n";
            found = true;
        }
        List <Bank> banks = bankRepository.findByidaccount(current_account);
        for (Bank bank : banks) {
            output=output + "User ID: " + bank.getId() +"\n";
            found1 = true;

        }

        if(found == false || found1 == false){
            output = "USSD ID or Bank account ID does not exist or is incorrect \n";
        }
        else {
            Connection new_connection = new Connection();
            connection_id = RANDOM.nextInt(max) + min;
            new_connection.setConnectionid(connection_id);
            new_connection.setUserid(current_id);
            new_connection.setPaymentid(current_account);
            connectionRepository.save(new_connection);

            output = output + "Payment successfully connected\n Please close the application";
        }
        return output;
    }


        public String process(String[] commands, String command) {
            if (commands == null || commands.length <= 2) {
                return enterID();
            }
            else{
                switch (commands.length){
                    case 3:
                        return findbyid(commands[2]);
                    case 4:
                        return complete();

                }
            }
            return "Nothing";
        }
}

