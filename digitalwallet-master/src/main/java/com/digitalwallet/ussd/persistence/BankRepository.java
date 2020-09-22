package com.digitalwallet.ussd.persistence;

import com.digitalwallet.ussd.domain.Bank;
import com.digitalwallet.ussd.domain.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Long> {
    List <Bank> findByidaccount(int idaccount);
    List <Bank> findAll();
    List <Bank> findBybankid(int bankid);
}
