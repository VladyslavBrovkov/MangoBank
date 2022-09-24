package com.example.mangobank.repository;

import com.example.mangobank.entity.RootUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootUserRepository extends JpaRepository<RootUser,Long> {
}
