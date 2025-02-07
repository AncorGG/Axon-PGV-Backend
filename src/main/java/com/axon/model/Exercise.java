package com.axon.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_exercise")
    private Long id;
    
    @Column(name = "exercise_name")
    private String exercise_name;
    
    @Column(name = "difficulty")
    private Integer difficulty;
    
    @Column(name = "speed")
    private Float speed;
    
    @Column(name = "experience")
    private Integer experience;

    public Exercise() {}

    public Long getId_exercise() {
        return id;
    }

    public void setId_exercise(Long id_exercise) {
        this.id = id_exercise;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}