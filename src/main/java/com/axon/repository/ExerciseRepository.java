package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
	//Personalized Methods
}
