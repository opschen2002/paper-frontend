package com.opschen.paperbackend.repository;

import com.opschen.paperbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);
}