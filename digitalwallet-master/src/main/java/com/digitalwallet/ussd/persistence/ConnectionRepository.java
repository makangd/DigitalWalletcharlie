package com.digitalwallet.ussd.persistence;

import com.digitalwallet.ussd.domain.Bank;
import com.digitalwallet.ussd.domain.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
    List<Connection> findByconnectionid(String connectionid);
    List <Connection> findAll();
    List <Connection> findByuserid(int userid);

}
