package com.example.StudentManagement_demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentManagement_demo.entity.Student;

// Handles database operations using JPA
// No need to write SQL queries manually

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByUserUsername(String username, Pageable pageable);
}
