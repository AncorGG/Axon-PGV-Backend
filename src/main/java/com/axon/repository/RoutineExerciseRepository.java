package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.axon.model.RoutineExercise;
import com.axon.model.RoutineExerciseKey;

public interface RoutineExerciseRepository extends JpaRepository<RoutineExercise, RoutineExerciseKey>{
	//Select Exercises with Routine ID
	List<RoutineExercise> findByRoutine_Id(Long id);
	
	//Select ExerciseRoutine with IDs
	RoutineExercise findByRoutine_IdAndExercise_Id(Long id_routine, Long id_exercise);

}
