package com.example.mangobank.repository;
import com.example.mangobank.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Long> {
//todo move to the UserRepository
    //    @Query("SELECT COUNT(l)>0 FROM User u JOIN u.loginData l WHERE l.loginEmail = :email")
//    //@Query(value = "SELECT COUNT(l)>0 FROM `User` u JOIN u.loginData  WHERE l.loginData = :email", nativeQuery = true)
//    boolean existByEmail(@Param("email") String email);

}
