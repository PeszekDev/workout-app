package com.example.workoutapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.Objects;

public class WorkoutSetDto {
    private Long id;
    private Integer reps;
    private Double weight;
    private LocalDateTime date;
    private Long exerciseId;


    public WorkoutSetDto() {
    }

    public WorkoutSetDto(Long id, Integer reps, Double weight, LocalDateTime date, Long exerciseId) {
        this.id = id;
        this.reps = reps;
        this.weight = weight;
        this.date = date;
        this.exerciseId = exerciseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutSetDto that = (WorkoutSetDto) o;
        return Objects.equals(id, that.id) && Objects.equals(reps, that.reps) && Objects.equals(weight, that.weight) && Objects.equals(date, that.date) && Objects.equals(exerciseId, that.exerciseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reps, weight, date, exerciseId);
    }
}
