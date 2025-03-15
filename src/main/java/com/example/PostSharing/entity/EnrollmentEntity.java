package com.example.PostSharing.entity;

import com.example.PostSharing.data.Branch;
import com.example.PostSharing.data.Direction;
import com.example.PostSharing.data.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+998\\d{9}",message = "Phone number must be in the format +9989XXXXXXXX")
    private String phoneNumber;

    @NotNull(message = "Direction is required")
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @NotNull(message = "Branch is required")
    @Enumerated(EnumType.STRING)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;



}
