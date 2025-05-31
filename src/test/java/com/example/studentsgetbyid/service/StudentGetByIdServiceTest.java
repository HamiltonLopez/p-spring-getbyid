package com.example.studentsgetbyid.service;

import com.example.studentsgetbyid.model.Student;
import com.example.studentsgetbyid.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class StudentGetByIdServiceTest {

    @Mock
    private StudentRepository repository;

    private StudentGetByIdService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new StudentGetByIdService(repository);
    }

    @Test
    void getStudentById_Success() {
        // Arrange
        Long id = 1L;
        Student expectedStudent = new Student("Test Name", "test@email.com", 20);
        when(repository.findById(id)).thenReturn(Optional.of(expectedStudent));

        // Act
        Optional<Student> result = service.getStudentById(id);

        // Assert
        assertTrue(result.isPresent());
        Student student = result.get();
        assertEquals("Test Name", student.getName());
        assertEquals("test@email.com", student.getEmail());
        assertEquals(20, student.getAge());
        verify(repository).findById(id);
    }

    @Test
    void getStudentById_NotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Optional<Student> result = service.getStudentById(1L);

        // Assert
        assertFalse(result.isPresent());
        verify(repository).findById(1L);
    }
} 