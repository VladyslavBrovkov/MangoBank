package com.example.mangobank.repository;

import com.example.mangobank.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("SELECT a FROM Account a WHERE a.iban= :iban")
    Optional<Account> findByIban(@Param("iban") String iban);

    @Query("SELECT a FROM Account a WHERE a.cardNumber= :cardNumber")
    Optional<Account> findByCardNumber(@Param("cardNumber") String cardNumber);

}
