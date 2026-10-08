package com.example.workoutapp.controller;

import com.example.workoutapp.dto.ExerciseDto;
import com.example.workoutapp.model.Exercise;
import com.example.workoutapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;


    @GetMapping
    public List<ExerciseDto> getExercise() {
        return exerciseService.getAllExercise();
    }

    @GetMapping
            (path = "{exerciseId}")
    public ExerciseDto getExerciseById(@PathVariable("exerciseId") Long id) {
        return exerciseService.getExerciseById(id);
    }

    @PostMapping
    public void registerExercise(@Valid @RequestBody ExerciseDto exerciseDto) {
        exerciseService.addNewExercise(new ExerciseDto());
    }

    @DeleteMapping(path = "{exerciseId}")
    public void deleteExerciseById(@PathVariable("exerciseId") Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
    }

}
