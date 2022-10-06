package com.example.mangobank.repository;
import com.example.mangobank.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Long> {
    @Query(value = "SELECT COUNT(l)>0 FROM login_data l WHERE l.login_email= :email", nativeQuery = true)
    public boolean findExistByEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(l)>0 FROM login_data l WHERE l.secret_word= :secretWord", nativeQuery = true)
    public boolean findExistBySecretWord(@Param("secretWord") String secretWord);

    @Query(value = "SELECT l.id FROM login_data l WHERE l.secret_word = :secretWord", nativeQuery = true)
    Long getIdBySecretWord(@Param("secretWord") String secretWord);
}
