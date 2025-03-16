package com.example.PostSharing.controller;

import com.example.PostSharing.dto.TeacherDTO;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    private final TeacherRepository teacherRepository;
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?>  register(@Valid @RequestBody TeacherDTO teacher) {
        TeacherEntity teacherEntity = new TeacherEntity();

        teacherEntity.setFullName(teacher.getFullName());
        teacherEntity.setSpeciality(teacher.getSpeciality());
        teacherEntity.setExperience(teacher.getExperience());
        teacherEntity.setEducation(teacher.getEducation());
        teacherEntity.setCertificates(teacher.getCertificates());
        teacherEntity.setPhoneNumber(teacher.getPhoneNumber());
        teacherEntity.setPhotoURL(teacher.getPhotoURL());
        teacherEntity.setVideoURL(teacher.getVideoURL());

        teacherRepository.save(teacherEntity);

        return ResponseEntity.ok("Successfully registered");
    }

    @GetMapping("/{id}/get_name")
    public ResponseEntity<String>  getTeacherEntityName(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getFullName()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_speciality")
    public ResponseEntity<String>  getTeacherEntitySpeciality(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getSpeciality()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_experience")
    public ResponseEntity<String>  getTeacherEntityExperience(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getExperience()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_education")
    public ResponseEntity<String>  getTeacherEntityEducation(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getEducation()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_certificates")
    public ResponseEntity<String>  getTeacherEntityCertificates(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getCertificates()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_phoneNumber")
    public ResponseEntity<String>  getTeacherEntityPhoneNumber(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getPhoneNumber()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_videoURL")
    public ResponseEntity<String>  getTeacherEntityVideoURL(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getVideoURL()))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/get_imageURL")
    public ResponseEntity<String> getTeacherEntityPhotoURL(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(teacher -> ResponseEntity.ok(teacher.getPhotoURL()))
                .orElse(ResponseEntity.notFound().build());
    }
}
