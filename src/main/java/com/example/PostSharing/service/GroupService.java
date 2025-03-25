package com.example.PostSharing.service;

import com.example.PostSharing.dto.GroupDTO;
import com.example.PostSharing.entity.GroupsEntity;
import com.example.PostSharing.entity.TeacherEntity;
import com.example.PostSharing.repository.GroupRepository;
import com.example.PostSharing.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;

    public GroupService(GroupRepository groupRepository, TeacherRepository teacherRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
    }

    public void saveGroup(GroupDTO groupDTO){

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
    }

    public void updateGroup(Long id, Map<String, Boolean> map){
        Boolean isActive = map.get("active");
        GroupsEntity groupEntity = this.groupRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Group not found"));
        groupEntity.setActive(isActive);
        groupRepository.save(groupEntity);
    }
}
