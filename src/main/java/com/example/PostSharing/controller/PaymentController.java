package com.example.PostSharing.controller;

import com.example.PostSharing.data.Month;
import com.example.PostSharing.dto.PaymentDTO;
import com.example.PostSharing.dto.PaymentResponseDTO;
import com.example.PostSharing.entity.PaymentEntity;
import com.example.PostSharing.service.PaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/add_payment")
    public ResponseEntity<?> addPayment(@Valid @RequestBody PaymentDTO paymentDTO) {
        paymentService.addPayment(paymentDTO);
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
        paymentService.confirmPayment(id);

        return ResponseEntity.ok("Payment confirmation successfully");
    }

}
