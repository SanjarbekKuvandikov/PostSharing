package com.example.PostSharing.entity;

import com.example.PostSharing.data.Month;
import com.example.PostSharing.data.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @NotNull(message = "Amount of sums cannot be empty")
    private Double amount;

    private Double paidAmount;

    @NotNull(message = "Paid date cannot be empty")
    private LocalDate paidDate;

    private Boolean isConfirmed;

    @NotNull(message = "Year cannot be empty")
    private Integer year;

    @Enumerated(EnumType.STRING)
    private Month month;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentEntity() {

    }

    public boolean isFullyPaid(){
        return this.paidAmount != null && this.paidAmount.equals(this.amount);
    }

    public void confirmPayment(){
        if (this.isFullyPaid()){
            this.isConfirmed = true;
        }else {
            throw new RuntimeException("Payment not fully paid");
        }
    }

    public void updatePaymentStatus(){
        this.status = this.calculatePaymentStatus();
    }

    public PaymentStatus calculatePaymentStatus(){
        if (this.paidAmount == null || this.paidAmount == 0){
            return PaymentStatus.UNPAID;
        }else if (this.paidAmount.equals(this.amount)){
            return PaymentStatus.PAID;
        }
        return PaymentStatus.PARTIALLY_PAID;
    }

    @PrePersist
    @PreUpdate
    public void autoUpdateStatus() {
        this.updatePaymentStatus();
    }

    public PaymentEntity(StudentEntity student, Integer year, Month month, Double amount) {
        this.student = student;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

}
