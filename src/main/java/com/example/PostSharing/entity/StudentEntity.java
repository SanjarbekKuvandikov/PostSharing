package com.example.PostSharing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "\\+998\\d{9}",message = "Phone number must be in the format +9989XXXXXXXX")
    private String phoneNumber;

    @NotNull(message = "Active cannot be empty")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "group_id",nullable = false)
    private GroupsEntity group;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<PaymentEntity> payments;

    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    private AchievementEntity achievementEntity;
}
