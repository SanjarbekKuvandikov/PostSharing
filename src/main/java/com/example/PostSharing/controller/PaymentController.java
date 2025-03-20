package com.example.PostSharing.controller;

import com.example.PostSharing.data.Month;
import com.example.PostSharing.dto.PaymentDTO;
import com.example.PostSharing.dto.PaymentResponseDTO;
import com.example.PostSharing.entity.GroupsEntity;
import com.example.PostSharing.entity.PaymentEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.repository.PaymentRepository;
import com.example.PostSharing.repository.StudentRepository;
import com.example.PostSharing.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final PaymentService paymentService;

    public PaymentController(PaymentRepository paymentRepository, StudentRepository studentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
        this.paymentService = paymentService;
    }

    @PostMapping("/add_payment")
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        StudentEntity student = studentRepository.findById(paymentDTO.getStudentId()).
                orElseThrow(() -> new RuntimeException("Student not found"));

        GroupsEntity group = student.getGroup();
        if (group == null) {
            throw new RuntimeException("Student does not have group");
        }

        PaymentEntity payment = paymentRepository.findByStudentIdAndYearAndMonth(
                paymentDTO.getStudentId(),
                        paymentDTO.getYear(),
                        paymentDTO.getMonth()).
                orElse(new PaymentEntity(student,paymentDTO.getYear(),paymentDTO.getMonth(),group.getFee()));

        double totalPaid = (payment.getPaidAmount() != null ? payment.getPaidAmount() : 0) +paymentDTO.getPaidSum();
        if (totalPaid > payment.getAmount()) {
            throw new RuntimeException("Paid amount exceeds paid amount");
        }

        payment.setPaidAmount(totalPaid);
        payment.setPaidDate(LocalDate.now());
        payment.updatePaymentStatus();

        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment added successfully");
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<PaymentResponseDTO>> getMonthly(
            @RequestParam("year") @NotNull @Min(2023) Integer year,
            @RequestParam("month") @NotNull Month month){
        List<PaymentResponseDTO> payments = paymentService.getPaymentsMonthly(year, month);

        return ResponseEntity.ok(payments);
    }

    @PostMapping("/create_next_month")
    public ResponseEntity<List<PaymentEntity>> createNextMonth(
            @RequestParam @NotNull @Min(2024) Integer year,
            @RequestParam @NotNull Month month){
        List<PaymentEntity> newPayments = paymentService.createPaymentForNextMonth(year, month);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPayments);
    }

    @PostMapping("/payments/{id}/confirm")
    public ResponseEntity<?> confirmPayment(@PathVariable Long id) {
        PaymentEntity payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.confirmPayment();
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment confirmation successfully");
    }

}
