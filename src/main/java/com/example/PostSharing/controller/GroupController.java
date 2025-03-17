package com.example.PostSharing.controller;

import com.example.PostSharing.dto.GroupDTO;
import com.example.PostSharing.entity.GroupsEntity;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.GroupRepository;
import com.example.PostSharing.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/groups")
public class GroupController {
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public GroupController(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody GroupDTO groupDTO){

        GroupsEntity groupEntity = new GroupsEntity();
        groupEntity.setName(groupDTO.getName());
        groupEntity.setDirection(groupDTO.getDirection());
        groupEntity.setStartDate(groupDTO.getStartDate());
        groupEntity.setEndDate(groupDTO.getEndDate());
        groupEntity.setActive(groupDTO.isActive());

        TeacherEntity teacher = this.teacherRepository.findById(groupDTO.getTeacherId()).
                orElseThrow(() -> new RuntimeException("Teacher not found"));

        groupEntity.setTeacher(teacher);

        groupRepository.save(groupEntity);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update_active")
    public ResponseEntity<?> updateActive(@PathVariable("id") Long id, @RequestBody Map<String , Boolean> request) {
        Boolean isActive = request.get("active");
        GroupsEntity groupEntity = this.groupRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Group not found"));
        groupEntity.setActive(isActive);
        groupRepository.save(groupEntity);

        return ResponseEntity.ok().build();
    }
}
