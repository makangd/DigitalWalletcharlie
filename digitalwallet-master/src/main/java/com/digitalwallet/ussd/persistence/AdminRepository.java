package com.digitalwallet.ussd.persistence;

import com.digitalwallet.ussd.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByFirstName(String firstName);
}
