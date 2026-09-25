package com.example.workoutapp.controller; // lub w podpakiecie exercise

import com.example.workoutapp.model.Exercise;
import com.example.workoutapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getExercise() {
        return exerciseService.getAllExercise();
    }

    @GetMapping(path = "{exerciseId}")
    public Exercise getExerciseById(@PathVariable("exerciseId") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public void registerExercise(@RequestBody Exercise exercise) {
        exerciseService.addNewExercise(exercise);
    }

    @DeleteMapping(path = "{exerciseId}")
    public void deleteExerciseById(@PathVariable("exerciseId") Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
    }

}
