package com.example.PostSharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentResponseDTO {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private Double amount;
    private Double paidAmount;
    private LocalDate paidDate;
    private String paymentStatus;
}
