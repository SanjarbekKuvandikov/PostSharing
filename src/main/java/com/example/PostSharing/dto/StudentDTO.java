package com.example.PostSharing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotNull(message = "Active cannot be empty")
    private boolean active;

    @NotNull(message = "Teacher id cannot be empty")
    private Long teacherId;

    @NotNull(message = "Group id cannot be empty")
    private Long groupId;
}
