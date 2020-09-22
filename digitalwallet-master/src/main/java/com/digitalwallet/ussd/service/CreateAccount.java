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
public class CreateAccount {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    private Login login;

    private static final Random RANDOM = new Random();


        String create_id(){
            return "Enter your SSN: \n";
        }
        String create_phonenumber(){
            return "Enter your phone number: \n";
        }
        String create_pin(){
            return "Create your 4 digit pin: \n";
        }
        String reenter_pin(){
        return "Re-enter your 4 digit pin: \n";
    }



        String set_ussd_account(String id, String phonenumber, String pin) {
            String output = "";
            int current_id = Integer.parseInt(id);
            int current_phonenumber = Integer.parseInt(phonenumber);
            int current_pin = Integer.parseInt(pin);

            List<User> users = userRepository.findByid(current_id);
            for (User user : users) {
                output = output+ "This SSN already exist in the system\n " +
                        "Your session has expired. \n Please restart the application \n" +
                        "or contact help desk for support.";
                   }
            User new_user = new User();
            new_user.setID(current_id);
            new_user.setPhonenumber(current_phonenumber);
            new_user.setPin(current_pin);
            userRepository.save(new_user);

            output = output + "USSD account created successfully \n SSN: " + current_id + "\n Phone number: " + current_phonenumber +
                "\n Thank for using the Digital Wallet Application";


            return output;
        }



        public String process(String[] commands, String command) {
            if (commands == null || commands.length <= 2) {
                return create_id();
            }
            else{
                switch (commands.length){

                    case 3:
                        return create_phonenumber();
                    case 4:
                        return create_pin();
                    case 5:
                        return reenter_pin();
                    case 6:
                        return set_ussd_account(commands[2], commands[3], commands[4]);

                }
            }
            return "Nothing";
        }
}

