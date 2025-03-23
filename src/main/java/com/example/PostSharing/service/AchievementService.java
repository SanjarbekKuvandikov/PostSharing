package com.example.PostSharing.service;

import com.example.PostSharing.dto.AchievementDTO;
import com.example.PostSharing.entity.AchievementEntity;
import com.example.PostSharing.entity.StudentEntity;
import com.example.PostSharing.repository.AchievementRepository;
import com.example.PostSharing.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AchievementService {
    private final StudentRepository studentRepository;
    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository, StudentRepository studentRepository) {
        this.achievementRepository = achievementRepository;
        this.studentRepository = studentRepository;
    }
    public void addAchievement(AchievementDTO achievementDTO){
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
    }

    public Map<String,Map<Double,Long>> getDetailStatics(){
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


        return Map.of("Listening", listeningStats,
                "Reading", readingStats,
                "Writing", writingStats,
                "Speaking", speakingStats,
                "Overall", overallStats);
    }

    public Map<String,Double> getAllStatistics(){
        double avgListening = achievementRepository.getAverageListeningScore();
        double avgReading = achievementRepository.getAverageReadingScore();
        double avgWriting = achievementRepository.getAverageWritingScore();
        double avgSpeaking = achievementRepository.getAverageSpeakingScore();
        double avgOverall = achievementRepository.getAverageOverallScore();

        return Map.of(
                "Listening",avgListening
                ,"Reading",avgReading
                ,"Writing",avgWriting
                ,"Speaking",avgSpeaking
                ,"Overall",avgOverall
        );
    }
}
