package com.axon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.axon.model.Routine;
import com.axon.repository.RoutineRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/routines")
public class RoutineController {

	@Autowired
	private RoutineRepository routineRepository;
	
	@GetMapping
	public List<Routine> getRoutines() {
		return routineRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Routine getRoutineById(@PathVariable("id") Long id) {
		return routineRepository.findById(id).get();
	}
	
	@PostMapping
	public ResponseEntity<Routine> insertRoutine(@RequestBody Routine routine) {
		Routine createdRoutine = routineRepository.save(routine);
	    return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Routine> updateRoutine(@PathVariable("id") Long id, @RequestBody Routine newRoutine) {
		return routineRepository.findById(id)
	            .map(routine -> {
	                routine.setRoutine_name(newRoutine.getRoutine_name());
	                routine.setDescription(newRoutine.getDescription());
	                routine.setId_user(newRoutine.getId_user());
	                routineRepository.save(routine);
	                return ResponseEntity.ok(routine);
	            })
	            .orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Routine deleteRoutine(@PathVariable("id") Long id) {
		Routine routine = routineRepository.findById(id).get();
		routineRepository.deleteById(id);
		return routine;
	}

}