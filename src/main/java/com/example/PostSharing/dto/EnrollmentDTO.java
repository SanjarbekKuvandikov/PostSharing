package com.example.PostSharing.dto;

import com.example.PostSharing.data.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentDTO {
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Direction is required")
    private String direction;

    @NotNull(message = "Branch is required")
    private String branch;

    private Status status = Status.NEW;

}
