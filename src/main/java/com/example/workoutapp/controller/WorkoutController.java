package com.example.workoutapp.controller;

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
    public List<WorkoutSet> getWorkoutSets() {
        return workoutService.getWorkoutSets();
    }

    @PostMapping(path = "{exerciseId}")
    public void registerNewWorkoutSet(@PathVariable("exerciseId") Long exerciseId,@Valid @RequestBody WorkoutSet workoutSet) {
        workoutService.addNewWorkoutSet(exerciseId, workoutSet);
    }

    @DeleteMapping(path = "{setId}")
    public void deleteWorkoutSet(@PathVariable("setId") Long setId) {
        workoutService.deleteWorkoutSet(setId);
    }
    @PutMapping(path = "{setId}")
    public void updateWorkoutSet(@PathVariable("setId") Long setId , Integer reps , Double weight) {
        workoutService.updateSetWorkout(setId , reps , weight);
    }

}