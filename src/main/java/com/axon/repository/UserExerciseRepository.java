package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.UserExercise;
import com.axon.model.UserExerciseKey;


public interface UserExerciseRepository extends JpaRepository<UserExercise, UserExerciseKey>{

}
