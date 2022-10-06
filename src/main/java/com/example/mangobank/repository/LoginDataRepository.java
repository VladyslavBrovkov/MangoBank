package com.example.mangobank.repository;
import com.example.mangobank.model.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDataRepository extends JpaRepository<LoginData,Long> {

}
