package com.example.PostSharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GroupDTO {

    @NotBlank(message = "Group name cannot be empty")
    private String name;

    @NotBlank(message = "Direction cannot be empty")
    private String direction;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Active cannot be empty")
    private boolean active;

    private Long teacherId;
}
