package com.example.PostSharing.controller;

import com.example.PostSharing.dto.StudentDTO;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);

        return ResponseEntity.ok("Student registered successfully");
    }
    @GetMapping("/get_all")
    public ResponseEntity<?> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/groups_student")
    public ResponseEntity<?> getGroupStudents(@PathVariable Long id) {
        List<StudentEntity> student = studentService.getGroupStudents(id);

        return ResponseEntity.ok(student);
    }
}
