package com.axon.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.axon.model.Routine;
import com.axon.repository.RoutineRepository;

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
	public Routine insertRoutine(@RequestBody Routine routine) {
		return routineRepository.save(routine);
	}
	
	@PutMapping("/{id}")
	public Routine updateRoutine(@PathVariable("id") Long id, @RequestBody Routine newRoutine) {
		Routine routine = routineRepository.findById(id).get();
		
		routine.setName(newRoutine.getName());
		routine.setDescription(newRoutine.getDescription());
		routine.setId_user(newRoutine.getId_user());
		
		return routine;
	}
	
	@DeleteMapping("/{id}")
	public Routine deleteRoutine(@PathVariable("id") Long id) {
		Routine routine = routineRepository.findById(id).get();
		routineRepository.deleteById(id);
		return routine;
	}

}
