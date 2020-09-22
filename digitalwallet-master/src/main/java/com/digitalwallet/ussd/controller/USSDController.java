package com.digitalwallet.ussd.controller;

import com.digitalwallet.ussd.service.Login;
import com.digitalwallet.ussd.service.Registration;
import com.digitalwallet.ussd.service.MainMenu;

import com.digitalwallet.ussd.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("ussd")
public class USSDController {
    @Autowired
    Login login;

    @Autowired
    Registration registration;

    @Autowired
    MainMenu mainmenu;

    @PostMapping("/")
    public String handleRequest(@RequestParam(value = "sessionId", required = false) String sessionId,
                                @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                @RequestParam(value = "networkCode", required = false) String networkCode,
                                @RequestParam(value = "serviceCode", required = false) String serviceCode,
                                @RequestParam(value = "text", required = false) String text
    ) {

        if (text == null || text.length() <=0) {
            return "CON " + mainmenu.process(null, 0);
        }else {
            String[] commands = text.split("\\*");
            return "CON " + mainmenu.process(commands, 0);
        }

    }

}
