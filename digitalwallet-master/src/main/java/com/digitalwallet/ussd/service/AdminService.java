package com.digitalwallet.ussd.service;
import com.digitalwallet.ussd.domain.User;
import com.digitalwallet.ussd.persistence.CompanyRepository;
import com.digitalwallet.ussd.persistence.OperatorRepository;
import com.digitalwallet.ussd.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminService {


    private CompanyRepository companyRepository;
    private OperatorRepository operatorRepository;
    private UserRepository userRepository;

    RestTemplate restTemplate = new RestTemplate();

    public AdminService(@Autowired CompanyRepository companyRepository, @Autowired OperatorRepository operatorRepository, @Autowired UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.operatorRepository = operatorRepository;
        this.userRepository = userRepository;
    }
/*
    public User createUser(String firstName, String lastName){
        return userRepository.save(new User(firstName, lastName));
    }

    public User findByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }
*/
    void callAPi(){
        restTemplate.getForEntity("url", String.class);
    }}
