package com.example.PostSharing.controller;

import com.example.PostSharing.dto.GroupDTO;
import com.example.PostSharing.service.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/groups")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody GroupDTO groupDTO){
        groupService.saveGroup(groupDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/update_active")
    public ResponseEntity<?> updateActive(@PathVariable("id") Long id, @RequestBody Map<String , Boolean> request) {
        groupService.updateGroup(id, request);

        return ResponseEntity.ok().build();
    }
}
