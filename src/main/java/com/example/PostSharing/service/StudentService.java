package com.example.PostSharing.service;

import com.example.PostSharing.dto.StudentDTO;
import com.example.PostSharing.entity.GroupsEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.GroupRepository;
import com.example.PostSharing.repository.StudentRepository;
import com.example.PostSharing.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    public void addStudent(StudentDTO studentDTO){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName(studentDTO.getFirstName());
        studentEntity.setLastName(studentDTO.getLastName());
        studentEntity.setPhoneNumber(studentDTO.getPhoneNumber());
        studentEntity.setActive(studentDTO.isActive());

        TeacherEntity teacher = teacherRepository.findById(studentDTO.getTeacherId()).
                orElseThrow(() -> new RuntimeException("Teacher not found"));

        studentEntity.setTeacher(teacher);

        GroupsEntity groups = groupRepository.findById(studentDTO.getGroupId()).
                orElseThrow(() -> new RuntimeException("Group not found"));

        studentEntity.setGroup(groups);

        studentRepository.save(studentEntity);
    }

    public List<StudentEntity> getAllStudents(){
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            throw new RuntimeException("No students found");
        }

        return students;
    }

    public List<StudentEntity> getGroupStudents(Long id){
        List<StudentEntity> students = studentRepository.findByGroup_Id(id);

        if (students.isEmpty()) {
            throw new RuntimeException("No students found");
        }

        return students;
    }
}
