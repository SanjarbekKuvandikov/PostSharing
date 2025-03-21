package com.example.PostSharing.repository;

import com.example.PostSharing.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    @Query("SELECT AVG (a.listening) FROM AchievementEntity a")
    Double getAverageListeningScore();

    @Query("SELECT AVG (a.reading) FROM AchievementEntity  a")
    Double getAverageReadingScore();

    @Query("SELECT AVG (a.writing) FROM AchievementEntity a")
    Double getAverageWritingScore();

    @Query("SELECT AVG (a.speaking) FROM AchievementEntity a")
    Double getAverageSpeakingScore();

    @Query("SELECT AVG (a.overall) FROM AchievementEntity a")
    Double getAverageOverallScore();
}
