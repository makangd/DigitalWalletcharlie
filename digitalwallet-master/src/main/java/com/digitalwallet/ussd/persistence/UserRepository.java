package com.digitalwallet.ussd.persistence;

import com.digitalwallet.ussd.domain.Company;
import com.digitalwallet.ussd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBypin(int pin);
    List<User> findByid(int id);


}
