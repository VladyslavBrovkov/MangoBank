package com.example.mangobank.repository;

import com.example.mangobank.models.entity.RootUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootUsersRepository extends JpaRepository<RootUsers,Long> {
}
