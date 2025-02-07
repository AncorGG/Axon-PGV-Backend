package com.axon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axon.model.Routine;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
	@Query("SELECT r FROM Routine r WHERE r.user.id = :userId")
	List<Routine> findByUserId(@Param("userId") Integer userId);
}