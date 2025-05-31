package com.example.studentsgetbyid.service;

import com.example.studentsgetbyid.model.Student;
import com.example.studentsgetbyid.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class StudentGetByIdService {

    private final StudentRepository repository;

    public StudentGetByIdService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }
} 