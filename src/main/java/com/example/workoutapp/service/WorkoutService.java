package com.example.workoutapp.service;

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

    public List<WorkoutSet> getWorkoutSets() {
        return workoutSetRepository.findAll();
    }

    public void addNewWorkoutSet(Long exerciseId, WorkoutSet workoutSet) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalStateException("Cwiczenie o id " + exerciseId + " nie istnieje"));

        workoutSet.setExercise(exercise);
        if (workoutSet.getDate() == null) {
            workoutSet.setDate(LocalDateTime.now());
        }
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
    }

}
