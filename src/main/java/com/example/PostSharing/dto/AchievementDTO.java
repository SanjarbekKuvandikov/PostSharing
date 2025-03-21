package com.example.PostSharing.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AchievementDTO {
    @NotNull(message = "Student id cannot be empty")
    private Long studentId;

    @NotNull(message = "Listening cannot be empty")
    private Double listening;

    @NotNull(message = "Reading cannot be empty")
    private Double reading;

    @NotNull(message = "Writing cannot be empty")
    private Double writing;

    @NotNull(message = "Speaking cannot be empty")
    private Double speaking;

    @NotBlank(message = "Certificate URL cannot be empty")
    private String certificate;
}
