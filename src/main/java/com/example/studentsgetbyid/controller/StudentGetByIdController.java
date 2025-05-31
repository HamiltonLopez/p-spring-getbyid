package com.example.studentsgetbyid.controller;

import com.example.studentsgetbyid.model.Student;
import com.example.studentsgetbyid.service.StudentGetByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentGetByIdController {

    private final StudentGetByIdService service;

    public StudentGetByIdController(StudentGetByIdService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return service.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 