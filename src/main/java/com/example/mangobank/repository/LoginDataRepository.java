package com.example.mangobank.repository;
import com.example.mangobank.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Long> {
    @Query("SELECT l FROM LoginData l WHERE l.loginEmail= :email")
    Optional<LoginData> findByEmail(@Param("email") String email);

    @Query("SELECT l FROM LoginData l WHERE l.secretWord= :secretWord")
    Optional<LoginData> findBySecretWord(@Param("secretWord") String secretWord);

}
