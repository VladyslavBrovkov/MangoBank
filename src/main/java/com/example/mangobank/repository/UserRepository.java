package com.example.mangobank.repository;

import com.example.mangobank.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT COUNT(l)>0 FROM User u JOIN u.loginData l WHERE l.loginEmail = :email")  // todo join doesn't needed here, think and refactor in future,
    //we just check that such email present in the LoginData table, it doesn't matter which user is linked
    boolean existByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u JOIN u.loginData l WHERE l.loginEmail = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT u.id FROM Users u WHERE u.login_data= (SELECT l.id FROM login_data l WHERE l.login_email = :email)", nativeQuery = true) //todo query looks overcomplicated
    Long getIdByEmail(@Param("email") String email);

    @Query(value = "SELECT u.id FROM Users u WHERE u.phone = :phone", nativeQuery = true) //todo why we use nativeQuery everywhere?
    Long getIdByPhone(@Param("phone") String phone);

    @Query(value = "SELECT COUNT(p)>0 FROM Users p WHERE phone = :phone", nativeQuery = true)
    boolean findExistByPhone(@Param("phone") String phone);

    @Query(value = "SELECT * FROM Users WHERE id = :id", nativeQuery = true)
    User getUserById(@Param("id") Long id);
}
