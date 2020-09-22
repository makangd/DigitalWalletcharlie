package com.digitalwallet.ussd.service;

import java.util.*;
import java.io.*;
import java.util.List;

import com.digitalwallet.ussd.persistence.UserRepository;
import com.digitalwallet.ussd.domain.User;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class Login{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInterface userinterface;

    @Autowired
    private Registration registration;


    public String findBypin(String pin){
        String output = "";
        int current_pin= Integer.parseInt(pin);
        boolean found = false;
        /*
        output = "Current Pin: "+current_pin;
        */
        /**/
        List <User> users = userRepository.findBypin(current_pin);
        for (com.digitalwallet.ussd.domain.User user : users){
            output = output + "Phone number: "+user.getPhoneNumber() + "\n" +
                    "ID: "+ user.getID() + "\n" +
                    "Please enter 1 to proceed.";
            found =true;
        }

        if(found == false){
            output ="Pin is incorrect. \n" +
                    "Your session has expired. \n" +
                    "Please restart the application.";
        }
        return output;
    }

    String enter_pin(){
        return ("Enter your PIN to login..");
    }

    String handletransition(String command, String[] commands){

        switch(Integer.parseInt(command)){
            case 1:
                return userinterface.processCommand(commands, command);
            case 2:
                return "Invalid input \n Your session has expired. \n Please restart the application.";
        }
        return null;
    }

    String handleServiceCommand(String[] commands) {
        //find what service it is
        int service = Integer.parseInt(commands[2]);
        //1 is insurance
        //pass command to service command handler
        if (service == 1) {
            return userinterface.processCommand(commands, commands[commands.length-1 ]);
        }

        return "Login Commands: "+commands;
    }

    public String process(String[] commands, String command) {
        if (commands == null || commands.length <= 1) {
            return enter_pin();
        } else {
            switch (commands.length) {
                case 2:
                    return findBypin(commands[1]);
                case 3:
                    return handletransition(commands[2], commands);
                default:
                    return handleServiceCommand(commands);
            }
        }
    }
}