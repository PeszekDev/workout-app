package com.example.workoutapp.controller;

import com.example.workoutapp.dto.WorkoutSetDto;
import com.example.workoutapp.model.WorkoutSet;
import com.example.workoutapp.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/sets")
public class WorkoutController {

    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<WorkoutSetDto> getWorkoutSets() {
        return workoutService.getWorkoutSets();
    }

    @PostMapping(path = "{exerciseId}")
    public void registerNewWorkoutSet(@PathVariable("exerciseId") Long exerciseId,@Valid @RequestBody WorkoutSetDto workoutSetDto) {
        workoutService.addNewWorkoutSet(exerciseId, workoutSetDto);
    }

    @DeleteMapping(path = "{setId}")
    public void deleteWorkoutSet(@PathVariable("setId") Long setId) {
        workoutService.deleteWorkoutSet(setId);
    }
    @PutMapping(path = "{setId}")
    public void updateWorkoutSet(@PathVariable("setId") Long setId , @RequestParam(required = false) Integer reps , @RequestParam(required = false) Double weight) {
        workoutService.updateSetWorkout(setId , reps , weight);
    }

}