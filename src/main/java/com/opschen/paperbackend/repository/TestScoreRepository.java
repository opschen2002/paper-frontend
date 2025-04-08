package com.opschen.paperbackend.repository;

import com.opschen.paperbackend.entity.TestScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestScoreRepository extends JpaRepository<TestScore, Long> {
}