package com.example.PostSharing.service;

import com.example.PostSharing.data.Month;
import com.example.PostSharing.data.PaymentStatus;
import com.example.PostSharing.dto.PaymentResponseDTO;
import com.example.PostSharing.entity.PaymentEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.repository.PaymentRepository;
import com.example.PostSharing.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public List<PaymentResponseDTO> getPaymentsMonthly(Integer year, Month month) {

        List<StudentEntity> student = studentRepository.findAll();

        return student.stream()
                .map(students -> {
                    PaymentEntity payment = paymentRepository.findByStudentIdAndYearAndMonth(students.getId(),
                            year,month).
                            orElse(new PaymentEntity(students,year,month,students.getGroup().getFee()));

                    PaymentResponseDTO dto = new PaymentResponseDTO();
                    dto.setStudentId(students.getId());
                    dto.setStudentFirstName(students.getFirstName());
                    dto.setStudentLastName(students.getLastName());
                    dto.setAmount(payment.getAmount());
                    dto.setPaidAmount(payment.getPaidAmount());
                    dto.setPaidDate(payment.getPaidDate());
                    dto.setPaymentStatus(payment.getStatus().toString());

                    return dto;
                }).collect(Collectors.toList());
    }

    public List<PaymentEntity> createPaymentForNextMonth(Integer year, Month month) {

        List<StudentEntity> students = studentRepository.findAll();

        List<PaymentEntity> newPayments = students.stream()
                .map(student -> {
                    if (paymentRepository.existsByStudentIdAndYearAndMonth(student.getId(),year,month)){
                        return null;
                    }
                    PaymentEntity payment = new PaymentEntity();
                    payment.setStudent(student);
                    payment.setYear(year);
                    payment.setMonth(month);
                    payment.setAmount(student.getGroup().getFee());

                    payment.setPaidAmount(0.0);
                    payment.setStatus(PaymentStatus.UNPAID);

                    return payment;
                })
                .filter(Objects::nonNull)
                .toList();

        return paymentRepository.saveAll(newPayments);
    }
}
