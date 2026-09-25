package com.example.workoutapp.config;

import com.example.workoutapp.model.Exercise;
import com.example.workoutapp.repository.ExerciseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WorkoutConfig {

    @Bean
    CommandLineRunner commandLineRunner(ExerciseRepository exerciseRepository) {
        return args -> {
            if (exerciseRepository.count() == 0) {
                Exercise benchPress = new Exercise("Wyciskanie na lawce");
                Exercise squat = new Exercise("Przysiad ze sztanga");
                exerciseRepository.saveAll(List.of(benchPress, squat));
            }
        };
    }
}
