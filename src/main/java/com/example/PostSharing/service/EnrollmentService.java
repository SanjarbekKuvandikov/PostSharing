package com.example.PostSharing.service;

import com.example.PostSharing.data.Status;
import com.example.PostSharing.entity.EnrollmentEntity;
import com.example.PostSharing.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<EnrollmentEntity> getNewEnrollments() {
        return enrollmentRepository.findByStatus(Status.NEW);
    }

    public void updateStatus(Long id, Status status) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Not found"));
        enrollmentEntity.setStatus(status);
        enrollmentRepository.save(enrollmentEntity);
    }

}
