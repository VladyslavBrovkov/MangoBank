package com.example.mangobank.repository;

import com.example.mangobank.model.entity.Account;
import com.example.mangobank.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    //todo example query spring data select by property
//    @Query(
//            value = "SELECT * FROM USERS u WHERE u.status = 1",
//            nativeQuery = true)
    List<Payment> findByFromAccount(Account account);
    List<Payment> findByToAccount(Account account);

}
