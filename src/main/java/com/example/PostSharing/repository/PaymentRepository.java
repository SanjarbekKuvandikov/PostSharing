package com.example.PostSharing.repository;

import com.example.PostSharing.data.Month;
import com.example.PostSharing.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findByStudentIdAndYearAndMonth(Long studentId, Integer year, Month month);
    boolean existsByStudentIdAndYearAndMonth(Long studentId, Integer year, Month month);
}
