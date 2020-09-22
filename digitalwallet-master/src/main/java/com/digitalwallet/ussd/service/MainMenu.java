package com.digitalwallet.ussd.service;

import com.digitalwallet.ussd.domain.User;
import com.digitalwallet.ussd.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainMenu {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Login login;

    @Autowired
    private Registration registration;


    String login_menu() {
        return ("Welcome to the USSD Digital Wallet\n" +
                "Enter the one of the following option(s): \n" +
                "1. Login \n" +
                "2. Register \n" +
                "3. Exit \n");
    }

    String handleLoginSelection(String command, String[] commands) {
        switch (Integer.parseInt(command)) {
            case 1:
                return login.process(commands, command);
            case 2:
                return registration.process(commands, command);
        }
        return "Thank you for using USSD Digital Wallet";
    }


    String handleServiceCommand(String[] commands) {
        //find what service it is
        int service = Integer.parseInt(commands[0]);
        //1 is insurance
        //pass command to service command handler
        if (service == 1) {
            return login.process(commands, commands[commands.length-1 ]);
        }
        else if(service == 2)
            return registration.process(commands, commands[commands.length-1 ]);

        return "Invalid input \nPlease enter the correct number";
    }

    public String process(String[] commands, int level) {
        if (commands == null || commands.length <= 0) {
            return login_menu();
        } else {
            switch (commands.length) {
                case 1:
                    return handleLoginSelection(commands[0], commands);
                default:
                    return handleServiceCommand(commands);
            }
        }
    }
}