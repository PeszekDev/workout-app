package com.example.workoutapp.service; // lub w podpakiecie exercise

import com.example.workoutapp.model.Exercise;
import com.example.workoutapp.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercise() {
        return exerciseRepository.findAll();
    }

    public Exercise getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ćwiczenie o id " + id + " nie znaleziono!"));
    }

    public Exercise addNewExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        boolean exists = exerciseRepository.existsById(id);
                if(!exists) {
                    throw new IllegalStateException("Ćwiczenie o id " + id + " nie znaleziono!");
                }
         exerciseRepository.deleteById(id);
    }
}
