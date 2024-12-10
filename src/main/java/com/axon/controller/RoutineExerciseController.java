package com.axon.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.model.Exercise;
import com.axon.model.Routine;
import com.axon.model.RoutineExercise;
import com.axon.model.RoutineExerciseKey;
import com.axon.repository.ExerciseRepository;
import com.axon.repository.RoutineExerciseRepository;
import com.axon.repository.RoutineRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/routine-exercise")
public class RoutineExerciseController {

    @Autowired
    private RoutineExerciseRepository routineExercisesRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping
    public List<RoutineExercise> getAllRoutineExercises() {
        return routineExercisesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Map<String, Object>>> findExercisesByRoutineId(@PathVariable("id") Long id_routine) {
        List<RoutineExercise> routineExercises = routineExercisesRepository.findByRoutine_Id(id_routine);
        
        if (routineExercises.isEmpty()) {
            System.out.println("No exercises found for routine ID: " + id_routine);
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<Map<String, Object>> responseList = routineExercises.stream()
                .sorted(Comparator.comparingInt(RoutineExercise::getSequence_order))
                .map(routineExercise -> {
                    Map<String, Object> exerciseData = new HashMap<>();
                    exerciseData.put("exercise", routineExercise.getExercise());
                    exerciseData.put("sequenceOrder", routineExercise.getSequence_order());
                    return exerciseData;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
    
    @PostMapping("/{routineId}/{exerciseId}/{sequenceOrder}")
    public ResponseEntity<?> addExerciseToRoutine(@PathVariable Long routineId, 
    		@PathVariable Long exerciseId,  @PathVariable Integer sequenceOrder) {
        
        try {
        	RoutineExerciseKey key = new RoutineExerciseKey(routineId, exerciseId);
        	
            RoutineExercise routineExercise = new RoutineExercise();
            routineExercise.setId(key);

            Routine routine = routineRepository.findById(routineId).orElseThrow();
            Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow();
            
            routineExercise.setRoutine(routine);
            routineExercise.setExercise(exercise);
            routineExercise.setSequence_order(sequenceOrder);
            
            RoutineExercise createdRoutineExercise = routineExercisesRepository.save(routineExercise);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutineExercise);
            
        } catch(Exception e) {
        	e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error occurred: " + e.getMessage());
        }
    }



}
