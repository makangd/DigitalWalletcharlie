package com.digitalwallet.ussd.service;

/*import org.graalvm.compiler.serviceprovider.ServiceProvider;*/
import org.springframework.stereotype.Service;

@Service
public class Services {
    String getServices(){
        return ("CON Please select service \n" +
                "1: Insurance\n");
    }
}
