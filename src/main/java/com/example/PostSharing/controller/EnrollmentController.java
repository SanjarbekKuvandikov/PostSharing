package com.example.PostSharing.controller;

import com.example.PostSharing.dto.EnrollmentDTO;
import com.example.PostSharing.entity.EnrollmentEntity;
import com.example.PostSharing.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        enrollmentService.addEnrollment(enrollmentDTO);
        return ResponseEntity.ok("Successfully registered");
    }

    @GetMapping("all")
    public ResponseEntity<List<EnrollmentEntity>> getAllEnrollments() {
        return ResponseEntity.of(Optional.ofNullable(enrollmentService.getAllEnrollments()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long id, @RequestParam() String statusString) {
        enrollmentService.updateStatus(id, statusString);
        return ResponseEntity.ok("Successfully updated");
    }

    @GetMapping("/only_new")
    public ResponseEntity<List<EnrollmentEntity>> getOnlyNewEnrollments() {
        return ResponseEntity.of(Optional.ofNullable(enrollmentService.getNewEnrollments()));

    }
}
