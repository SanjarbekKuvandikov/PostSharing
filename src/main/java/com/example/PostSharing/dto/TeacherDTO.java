package com.example.PostSharing.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    @NotBlank(message = "Speciality cannot be empty")
    private String speciality;

    @NotBlank(message = "Experience cannot be empty")
    private String experience;

    @NotBlank(message = "Education cannot be empty")
    private String education;

    @NotBlank(message = "Certificates cannot be empty")
    private String certificates;

    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @NotBlank(message = "PhotoURL cannot be empty")
    private String photoURL;

    @NotBlank(message = "VideoURL cannot be empty")
    private String videoURL;
}
