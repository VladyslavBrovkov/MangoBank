package com.example.mangobank.repository;

import com.example.mangobank.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query(value = "from Payment p where p.paymentTime BETWEEN :startDate AND :endDate")
    List<Payment> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

   // List<Payment> getAllBetweenAmount()//todo:check for amount jpql
}
