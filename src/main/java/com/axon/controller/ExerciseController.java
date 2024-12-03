package com.axon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.model.Exercise;
import com.axon.model.RoutineExercise;
import com.axon.repository.ExerciseRepository;
import com.axon.repository.RoutineExerciseRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private RoutineExerciseRepository routineExerciseRepository;
	
	@GetMapping
	public List<Exercise> getExercises() {
		return exerciseRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Exercise getExerciseById(@PathVariable("id") Long id) {
		return exerciseRepository.findById(id).get();
	}
	
	@DeleteMapping("/{id_routine}/{id_exercise}")
    public ResponseEntity<String> deleteExerciseByRoutineId(
    		@PathVariable("id_routine") Long id_routine, 
    		@PathVariable("id_exercise") Long id_exercise) {
    	
		try {
			RoutineExercise routineExercise = routineExerciseRepository.findByRoutine_IdAndExercise_Id(id_routine, id_exercise);
			
			if (routineExercise == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("RoutineExercise not found for the given routine and exercise IDs.");
	        }
			
			routineExerciseRepository.delete(routineExercise);
			
			return ResponseEntity.ok("Exercise removed from routine successfully.");
			
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                .body("Error occurred while removing exercise from routine.");
		}
    }
	
}