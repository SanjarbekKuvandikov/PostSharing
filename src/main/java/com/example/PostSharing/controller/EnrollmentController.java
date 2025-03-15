package com.example.PostSharing.controller;

import com.example.PostSharing.data.Branch;
import com.example.PostSharing.data.Direction;
import com.example.PostSharing.data.Status;
import com.example.PostSharing.dto.EnrollmentRequest;
import com.example.PostSharing.entity.EnrollmentEntity;
import com.example.PostSharing.repository.EnrollmentRepository;
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
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentRepository enrollmentRepository) {
        this.enrollmentService = enrollmentService;
        this.enrollmentRepository = enrollmentRepository;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody EnrollmentRequest request) {

        Direction direction;
        Branch branch;
        try {
            direction = Direction.valueOf(request.getDirection().toUpperCase());
            branch = Branch.valueOf(request.getBranch().toUpperCase());
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Invalid direction or branch");
        }
        EnrollmentEntity entity = new EnrollmentEntity();
        entity.setName(request.getName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setBranch(branch);
        entity.setDirection(direction);
        entity.setStatus(request.getStatus());

        enrollmentRepository.save(entity);
        return ResponseEntity.ok("Successfully registered");
    }

    @GetMapping("all")
    public ResponseEntity<List<EnrollmentEntity>> getAllEnrollments() {
        return ResponseEntity.of(Optional.ofNullable(enrollmentService.getAllEnrollments()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("id") Long id, @RequestParam() String statusString) {
        Optional<EnrollmentEntity> enrollmentOpt = enrollmentRepository.findById(id);
        if (enrollmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Enrollment not found");
        }

        try {
           Status status = Status.valueOf(statusString.toUpperCase());
            enrollmentService.updateStatus(id,status);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Error while updating status");
        }
        return ResponseEntity.ok("Successfully updated");
    }

    @GetMapping("/only_new")
    public ResponseEntity<List<EnrollmentEntity>> getOnlyNewEnrollments() {
        return ResponseEntity.of(Optional.ofNullable(enrollmentService.getNewEnrollments()));

    }
}
