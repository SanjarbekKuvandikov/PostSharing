package com.example.PostSharing.dto;

import com.example.PostSharing.data.Month;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentDTO {

    @NotNull(message = "Student ID cannot be empty")
    @Positive(message = "Student ID cannot be minus")
    private Long studentId;

    @NotNull(message = "Year cannot be empty")
    @Min(value = 2024, message = "A year cannot below 2024")
    private Integer year;

    @NotNull(message = "Month cannot be empty")
    private Month month;

    @NotNull(message = "Paid sums cannot be empty")
    @Positive(message = "Minus")
    private Double paidSum;

}
