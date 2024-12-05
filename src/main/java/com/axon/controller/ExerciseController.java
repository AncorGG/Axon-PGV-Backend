package com.axon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> deleteExerciseByRoutineId(
    		@PathVariable("id_routine") Long id_routine, 
    		@PathVariable("id_exercise") Long id_exercise) {
    	
		try {
			RoutineExercise routineExercise = routineExerciseRepository.findByRoutine_IdAndExercise_Id(id_routine, id_exercise);
			
			if (routineExercise == null) {
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("error", "RoutineExercise not found for the given routine and exercise IDs.");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	        }
			
			routineExerciseRepository.delete(routineExercise);
			
			Map<String, String> successResponse = new HashMap<>();
	        successResponse.put("message", "Exercise removed from routine successfully.");
	        return ResponseEntity.ok(successResponse);
			
		} catch (Exception e) {
			Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", "Error occurred while removing exercise from routine.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
    }
	
}