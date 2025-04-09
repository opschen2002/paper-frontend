package com.opschen.paperbackend.repository;

import com.opschen.paperbackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentId(String studentId);
}