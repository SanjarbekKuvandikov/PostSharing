package com.example.PostSharing.controller;

import com.example.PostSharing.dto.TeacherDTO;
import com.example.PostSharing.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<?>  register(@Valid @RequestBody TeacherDTO teacherDTO) {
        teacherService.addTeacher(teacherDTO);
        return ResponseEntity.ok("Successfully registered");
    }

    @GetMapping("/{id}/get_name")
    public ResponseEntity<String>  getTeacherEntityName(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherName(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_speciality")
    public ResponseEntity<String>  getTeacherEntitySpeciality(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherSpeciality(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_experience")
    public ResponseEntity<String>  getTeacherEntityExperience(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherExperience(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_education")
    public ResponseEntity<String>  getTeacherEntityEducation(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherEducation(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_certificates")
    public ResponseEntity<String>  getTeacherEntityCertificates(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherCertificates(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_phoneNumber")
    public ResponseEntity<String>  getTeacherEntityPhoneNumber(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherPhoneNumber(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_videoURL")
    public ResponseEntity<String>  getTeacherEntityVideoURL(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherVideoURL(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/get_imageURL")
    public ResponseEntity<String> getTeacherEntityPhotoURL(@PathVariable Long id) {
        try {
            String fullName = teacherService.getTeacherPhotoURL(id);
            return ResponseEntity.ok(fullName);
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
