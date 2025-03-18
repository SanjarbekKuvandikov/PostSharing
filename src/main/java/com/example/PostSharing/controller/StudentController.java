package com.example.PostSharing.controller;

import com.example.PostSharing.dto.StudentDTO;
import com.example.PostSharing.entity.GroupsEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.GroupRepository;
import com.example.PostSharing.repository.StudentRepository;
import com.example.PostSharing.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;

    public StudentController(StudentRepository studentRepository, TeacherRepository teacherRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody StudentDTO studentDTO) {
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

        return ResponseEntity.ok("Student registered successfully");
    }
    @GetMapping("/get_all")
    public ResponseEntity<?> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();

        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}/groups_student")
    public ResponseEntity<?> getGroupStudents(@PathVariable Long id) {
        List<StudentEntity> student = studentRepository.findByGroup_Id(id);

        if (student.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(student);
    }
}
