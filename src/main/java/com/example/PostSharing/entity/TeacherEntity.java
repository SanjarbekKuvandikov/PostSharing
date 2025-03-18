package com.example.PostSharing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @Pattern(regexp = "\\+998\\d{9}",message = "Phone number must be in the format +9989XXXXXXXX")
    private String phoneNumber;

    @NotBlank(message = "PhotoURL cannot be empty")
    private String photoURL;

    @NotBlank(message = "VideoURL cannot be empty")
    private String videoURL;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<GroupsEntity> groupsEntities;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<StudentEntity> studentEntities;

}
