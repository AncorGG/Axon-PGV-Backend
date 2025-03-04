package com.axon.config;

import com.axon.model.Badge;
import com.axon.model.Exercise;
import com.axon.model.Milestone;
import com.axon.repository.BadgeRepository;
import com.axon.repository.ExerciseRepository;
import com.axon.repository.MilestoneRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ExerciseRepository exerciseRepository, BadgeRepository badgeRepository, MilestoneRepository milestoneRepository) {
        return args -> {
            if (exerciseRepository.count() == 0) {
            	exerciseRepository.saveAll(List.of(
                    new Exercise(null, "Digit-Bash Easy", 4, 4f, 50),
                    new Exercise(null, "Digit-Bash Medium", 6, 3f, 100),
                    new Exercise(null, "Digit-Bash Hard", 8, 2f, 150),
                    new Exercise(null, "Digit-Bash Extreme", 10, 1.0f, 200),
                    new Exercise(null, "Digit-Bash Hardcore", 12, 0.5f, 250)
                ));
                System.out.println("Datos iniciales insertados en 'exercises'");
            }
            if (badgeRepository.count() == 0) {
                badgeRepository.saveAll(List.of(
                    new Badge(null, "Beginner", "Awarded for completing the first exercise", "Complete 1 exercise"),
                    new Badge(null, "Intermediate", "Awarded for completing five exercises", "Complete 5 exercises"),
                    new Badge(null, "Expert", "Awarded for completing ten exercises", "Complete 10 exercises"),
                    new Badge(null, "Champion", "Awarded for mastering all exercises", "Complete all exercises")
                ));
                System.out.println("Datos iniciales insertados en 'badge'");
            }
            if (milestoneRepository.count() == 0) {
                milestoneRepository.saveAll(List.of(
                    new Milestone(null, "First Steps", "Complete 1 workout", "10 XP", "1/10"),
                    new Milestone(null, "Halfway There", "Complete 5 workouts", "50 XP", "5/10"),
                    new Milestone(null, "Almost Done", "Complete 9 workouts", "90 XP", "9/10"),
                    new Milestone(null, "Master", "Complete 10 workouts", "100 XP", "10/10")
                ));
                System.out.println("Datos iniciales insertados en 'milestones'");
            }
        };
    }
}

