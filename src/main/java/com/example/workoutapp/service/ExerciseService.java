package com.example.workoutapp.service; // lub w podpakiecie exercise

import com.example.workoutapp.dto.ExerciseDto;
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

    public List<ExerciseDto> getAllExercise() {
        return exerciseRepository.findAll()
                .stream()
                .map(exercise -> new ExerciseDto(exercise.getId(),exercise.getName()))
                .toList();
    }

    public ExerciseDto getExerciseById(Long id) {
        return exerciseRepository.findById(id)
                .map(exercise -> new ExerciseDto(exercise.getId(), exercise.getName()))
                .orElseThrow(() -> new IllegalStateException("ćwiczenie o id " +id + " nie istnieje"));
    }

    public void addNewExercise(ExerciseDto exerciseDto) {
         Exercise exercise = new Exercise(exerciseDto.getName());
         exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        boolean exists = exerciseRepository.existsById(id);
                if(!exists) {
                    throw new IllegalStateException("Ćwiczenie o id " + id + " nie znaleziono!");
                }
         exerciseRepository.deleteById(id);
    }
}
