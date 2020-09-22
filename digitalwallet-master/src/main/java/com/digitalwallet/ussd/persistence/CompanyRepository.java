package com.digitalwallet.ussd.persistence;
import java.util.Optional;
import com.digitalwallet.ussd.domain.Company;
import com.digitalwallet.ussd.domain.Operator;
import com.digitalwallet.ussd.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.nio.file.LinkOption;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    List < Company > findAll();
    List<Company> findByid(long id);

    /*Company findBypolicynumber(String policynumber);*/
    List <Company> findBypolicynumber(String policynumber);

}
