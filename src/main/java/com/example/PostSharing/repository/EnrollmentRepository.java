package com.example.PostSharing.repository;

import com.example.PostSharing.data.Status;
import com.example.PostSharing.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Long> {
    List<EnrollmentEntity> findByStatus(Status status);
}
