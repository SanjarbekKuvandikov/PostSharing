package com.example.PostSharing.service;

import com.example.PostSharing.data.Branch;
import com.example.PostSharing.data.Direction;
import com.example.PostSharing.data.Status;
import com.example.PostSharing.dto.EnrollmentDTO;
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

    public void addEnrollment(EnrollmentDTO enrollmentDTO) {
        Direction direction;
        Branch branch;
        try {
            direction = Direction.valueOf(enrollmentDTO.getDirection().toUpperCase());
            branch = Branch.valueOf(enrollmentDTO.getBranch().toUpperCase());
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Invalid direction or branch");
        }
        EnrollmentEntity entity = new EnrollmentEntity();
        entity.setName(enrollmentDTO.getName());
        entity.setPhoneNumber(enrollmentDTO.getPhoneNumber());
        entity.setBranch(branch);
        entity.setDirection(direction);
        entity.setStatus(enrollmentDTO.getStatus());

        enrollmentRepository.save(entity);
    }

    public List<EnrollmentEntity> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<EnrollmentEntity> getNewEnrollments() {
        return enrollmentRepository.findByStatus(Status.NEW);
    }

    public void updateStatus(Long id, String statusString) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Not found"));
        Status status = Status.valueOf(statusString);

        if (status.equals(Status.NEW) || status.equals(Status.VIEWED) || status.equals(Status.REPLIED)){
            enrollmentEntity.setStatus(status);
            enrollmentRepository.save(enrollmentEntity);
        }

        throw new RuntimeException("Error while updating status");
    }

}
