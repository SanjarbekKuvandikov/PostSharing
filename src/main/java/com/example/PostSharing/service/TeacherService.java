package com.example.PostSharing.service;

import com.example.PostSharing.dto.TeacherDTO;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addTeacher(TeacherDTO teacherDTO){
        TeacherEntity teacherEntity = new TeacherEntity();

        teacherEntity.setFullName(teacherDTO.getFullName());
        teacherEntity.setSpeciality(teacherDTO.getSpeciality());
        teacherEntity.setExperience(teacherDTO.getExperience());
        teacherEntity.setEducation(teacherDTO.getEducation());
        teacherEntity.setCertificates(teacherDTO.getCertificates());
        teacherEntity.setPhoneNumber(teacherDTO.getPhoneNumber());
        teacherEntity.setPhotoURL(teacherDTO.getPhotoURL());
        teacherEntity.setVideoURL(teacherDTO.getVideoURL());

        teacherRepository.save(teacherEntity);
    }

    public String getTeacherName(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getFullName)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherSpeciality(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getSpeciality)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherExperience(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getExperience)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherEducation(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getEducation)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherCertificates(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getCertificates)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherPhoneNumber(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getPhoneNumber)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherPhotoURL(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getPhotoURL)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    public String getTeacherVideoURL(Long id){
        return teacherRepository.findById(id)
                .map(TeacherEntity::getVideoURL)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }
}
