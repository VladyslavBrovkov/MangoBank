package com.example.mangobank.repository;

import com.example.mangobank.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.id FROM Users u LEFT JOIN login_data l ON l.login_email= :email", nativeQuery = true)
    Long getIdByEmail(@Param("email") String email);

    @Query(value = "SELECT u.id FROM Users u WHERE u.phone = :phone", nativeQuery = true)
    Long getIdByPhone(@Param("phone") String phone);

    @Query(value = "SELECT COUNT(p)>0 FROM Users p WHERE phone = :phone", nativeQuery = true)
    boolean existByPhone(@Param("phone") String phone);

    @Query(value = "SELECT u FROM Users u WHERE u.id = :id", nativeQuery = true)
    User getUserById(@Param("id") Long id);

    @Query("SELECT COUNT(l)>0 FROM User u JOIN u.loginData l WHERE l.loginEmail = :email")
    boolean existByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u JOIN u.loginData l WHERE l.loginEmail = :email")
    User findByEmail(@Param("email") String email); //todo should return optional


}
