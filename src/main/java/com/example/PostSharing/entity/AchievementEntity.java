package com.example.PostSharing.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "achievement")
public class AchievementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false,unique = true)
    private StudentEntity student;

    @NotNull(message = "Listening cannot be empty")
    private Double listening;

    @NotNull(message = "Reading cannot be empty")
    private Double reading;

    @NotNull(message = "Writing cannot be empty")
    private Double writing;

    @NotNull(message = "Speaking cannot be empty")
    private Double speaking;

    @NotNull
    private Double overall;

    @NotBlank(message = "Certificate URL cannot be empty")
    private String certificate;

    public void updateOverall(){
        this.overall = (listening + reading + writing + speaking) / 4;
    }
}
