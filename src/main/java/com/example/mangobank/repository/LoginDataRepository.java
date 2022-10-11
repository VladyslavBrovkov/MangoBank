package com.example.mangobank.repository;
import com.example.mangobank.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Long> {
    @Query("SELECT COUNT(l)>0 FROM LoginData l WHERE l.loginEmail= :email")
    boolean existByEmail(@Param("email") String email);

    @Query("SELECT COUNT(l)>0 FROM LoginData l WHERE l.secretWord= :secretWord")
    boolean existBySecretWord(@Param("secretWord") String secretWord); //todo: findBySecretWord and move to UserRepo + return User Optional

    @Query("SELECT l.id FROM LoginData l WHERE l.secretWord = :secretWord")
    Long getIdBySecretWord(@Param("secretWord") String secretWord);
}
