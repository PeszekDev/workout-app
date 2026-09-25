package com.example.workoutapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;




@Entity
@Table(name = "workout_set")
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Liczba powtórzeń jest wymagana!")
    @Min(value = 1, message = "Liczba powtórzeń musi wynosić co najmniej 1!")
    private Integer reps;

    @NotNull(message = "Ciężar jest wymagany!")
    @Positive(message = "Ciężar musi być większy od zera!")
    private Double weight;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    @JsonIgnore
    private Exercise exercise;

    public WorkoutSet() {}

    public WorkoutSet(Integer reps, Double weight, LocalDateTime date, Exercise exercise) {
        this.reps = reps;
        this.weight = weight;
        this.date = date;
        this.exercise = exercise;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getReps() { return reps; }
    public void setReps(Integer reps) { this.reps = reps; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}
