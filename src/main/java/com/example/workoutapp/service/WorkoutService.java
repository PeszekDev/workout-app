package com.example.workoutapp.service;

import com.example.workoutapp.dto.ExerciseDto;
import com.example.workoutapp.dto.WorkoutSetDto;
import com.example.workoutapp.model.Exercise;
import com.example.workoutapp.model.WorkoutSet;
import com.example.workoutapp.repository.ExerciseRepository;
import com.example.workoutapp.repository.WorkoutSetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class WorkoutService {

    private final WorkoutSetRepository workoutSetRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutService(WorkoutSetRepository workoutSetRepository, ExerciseRepository exerciseRepository) {
        this.workoutSetRepository = workoutSetRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<WorkoutSetDto> getWorkoutSets() {
        return workoutSetRepository.findAll()
                .stream()
                .map(workoutSet -> new WorkoutSetDto(workoutSet.getId(), workoutSet.getReps() , workoutSet.getWeight() , workoutSet.getDate() , workoutSet.getExercise().getId()))
                .toList();
    }

    public void addNewWorkoutSet(Long exerciseId, WorkoutSetDto workoutSetDto) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalStateException("Cwiczenie o id " + exerciseId + " nie istnieje"));
            WorkoutSet workoutSet = new WorkoutSet();
            workoutSet.setReps(workoutSetDto.getReps());
            workoutSet.setWeight(workoutSetDto.getWeight());
            workoutSet.setDate(workoutSetDto.getDate() != null ? workoutSetDto.getDate() : LocalDateTime.now());
            workoutSet.setExercise(exercise);
        workoutSetRepository.save(workoutSet);
    }

    public void deleteWorkoutSet(Long setId) {
        boolean exists = workoutSetRepository.existsById(setId);
        if (!exists) {
            throw new IllegalStateException("Seria o id " + setId + " nie istnieje");
        }
        workoutSetRepository.deleteById(setId);
    }


    @Transactional
    public void updateSetWorkout(Long setId, Integer reps, Double weight) {
        WorkoutSet workoutSet = workoutSetRepository.findById(setId)
                .orElseThrow(() -> new IllegalStateException("Seria o id " + setId + " nie istnieje!"));
        if (reps != null && reps > 0 && !Objects.equals(workoutSet.getReps(), reps)) {
            workoutSet.setReps(reps);
        }
        if (weight != null && weight >= 0 && !Objects.equals(workoutSet.getWeight(), weight)) {
            workoutSet.setWeight(weight);
        }
        workoutSetRepository.save(workoutSet);
    }

}
