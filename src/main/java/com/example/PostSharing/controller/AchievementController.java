package com.example.PostSharing.controller;

import com.example.PostSharing.dto.AchievementDTO;
import com.example.PostSharing.entity.AchievementEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.repository.AchievementRepository;
import com.example.PostSharing.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/achievement")
public class AchievementController {
    private final StudentRepository studentRepository;
    private final AchievementRepository achievementRepository;

    public AchievementController(StudentRepository studentRepository,AchievementRepository achievementRepository) {
        this.studentRepository = studentRepository;
        this.achievementRepository = achievementRepository;
    }

    @PostMapping("/add_achievement")
    public ResponseEntity<?> addAchievement(@Valid @RequestBody AchievementDTO achievementDTO) {
        StudentEntity student = studentRepository.findById(achievementDTO.getStudentId()).
                orElseThrow(() -> new RuntimeException("Student not found"));

        AchievementEntity achievementEntity = new AchievementEntity();
        achievementEntity.setStudent(student);
        achievementEntity.setListening(achievementDTO.getListening());
        achievementEntity.setReading(achievementDTO.getReading());
        achievementEntity.setWriting(achievementDTO.getWriting());
        achievementEntity.setSpeaking(achievementDTO.getSpeaking());
        achievementEntity.setCertificate(achievementDTO.getCertificate());
        achievementEntity.updateOverall();
        achievementRepository.save(achievementEntity);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/detailed_statics")
    public ResponseEntity<?> getDetailedStatics() {
        List<AchievementEntity> achievements = achievementRepository.findAll();

        Map<Double, Long> listeningStats = achievements.stream()
                .collect(Collectors.groupingBy(AchievementEntity::getListening, Collectors.counting()));

        Map<Double, Long> readingStats = achievements.stream()
                .collect(Collectors.groupingBy(AchievementEntity::getReading, Collectors.counting()));

        Map<Double, Long> writingStats = achievements.stream()
                .collect(Collectors.groupingBy(AchievementEntity::getWriting, Collectors.counting()));

        Map<Double, Long> speakingStats = achievements.stream()
                .collect(Collectors.groupingBy(AchievementEntity::getSpeaking, Collectors.counting()));

        Map<Double, Long> overallStats = achievements.stream()
                .collect(Collectors.groupingBy(AchievementEntity::getOverall, Collectors.counting()));

        Map<String, Map<Double, Long>> stats = Map.of(
                "Listening", listeningStats,
                "Reading", readingStats,
                "Writing", writingStats,
                "Speaking", speakingStats,
                "Overall", overallStats
        );

        return ResponseEntity.ok().body(stats);
    }

    @GetMapping("/get_statics")
    public ResponseEntity<?> getStatics() {
        double avgListening = achievementRepository.getAverageListeningScore();
        double avgReading = achievementRepository.getAverageReadingScore();
        double avgWriting = achievementRepository.getAverageWritingScore();
        double avgSpeaking = achievementRepository.getAverageSpeakingScore();
        double avgOverall = achievementRepository.getAverageOverallScore();

        Map<String, Double> stats = Map.of(
                "Listening",avgListening
                ,"Reading",avgReading
                ,"Writing",avgWriting
                ,"Speaking",avgSpeaking
                ,"Overall",avgOverall
        );

        return ResponseEntity.ok().body(stats);
    }
}
