package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
	//Personalized methods
}
