package com.example.mangobank.repository;

import com.example.mangobank.entity.RootUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootUserRepository extends JpaRepository<RootUsers,Long> {
}
