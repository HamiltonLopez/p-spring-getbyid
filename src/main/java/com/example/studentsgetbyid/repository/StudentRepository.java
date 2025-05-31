package com.example.studentsgetbyid.repository;

import com.example.studentsgetbyid.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
} 