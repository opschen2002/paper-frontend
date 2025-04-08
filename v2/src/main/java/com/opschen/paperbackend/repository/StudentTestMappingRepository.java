package com.opschen.paperbackend.repository;

import com.opschen.paperbackend.entity.StudentTestMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTestMappingRepository extends JpaRepository<StudentTestMapping, Long> {
}