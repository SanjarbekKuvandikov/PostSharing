package com.example.PostSharing.controller;

import com.example.PostSharing.dto.AchievementDTO;
import com.example.PostSharing.service.AchievementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/achievement")
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @PostMapping("/add_achievement")
    public ResponseEntity<?> addAchievement(@Valid @RequestBody AchievementDTO achievementDTO) {
        achievementService.addAchievement(achievementDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/detailed_statics")
    public ResponseEntity<?> getDetailedStatics() {
        Map<String,Map<Double,Long>> stats = achievementService.getDetailStatics();
        return ResponseEntity.ok().body(stats);
    }

    @GetMapping("/get_statics")
    public ResponseEntity<?> getStatics() {
        Map<String, Double> stats = achievementService.getAllStatistics();
        return ResponseEntity.ok().body(stats);
    }
}
