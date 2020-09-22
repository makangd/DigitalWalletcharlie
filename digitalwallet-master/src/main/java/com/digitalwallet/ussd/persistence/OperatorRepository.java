package com.digitalwallet.ussd.persistence;

import com.digitalwallet.ussd.domain.Operator;
import com.digitalwallet.ussd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import java.nio.file.LinkOption;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    Operator findByid(String id);
    List < Operator > findAll();

}
