package com.example.PostSharing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class GroupsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Group name cannot be empty")
    private String name;

    @NotBlank(message = "Direction cannot be empty")
    private String direction;

    @NotNull(message = "Start date cannot be empty")
    private LocalDate startDate;

    @NotNull(message = "End date cannot be empty")
    private LocalDate endDate;

    @NotNull(message = "Active cannot be empty")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "teacher_id" ,nullable = false)
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    List<StudentEntity> studentEntities;

    @NotNull(message = "Group fee cannot be empty")
    private Double fee;
}
