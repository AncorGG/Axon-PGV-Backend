package com.axon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.axon.model.Milestone;
import com.axon.repository.MilestoneRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneRepository milestoneRepository;
    
    @GetMapping
    public List<Milestone> getMilestones() {
        return milestoneRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable("id") Long id) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);
        return milestone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Milestone> insertMilestone(@RequestBody Milestone milestone) {
        Milestone createdMilestone = milestoneRepository.save(milestone);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMilestone);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable("id") Long id, @RequestBody Milestone newMilestone) {
        return milestoneRepository.findById(id)
                .map(milestone -> {
                    milestone.setTitle(newMilestone.getTitle());
                    milestone.setObtention(newMilestone.getObtention());
                    milestone.setReward(newMilestone.getReward());
                    milestone.setProgress(newMilestone.getProgress());
                    milestoneRepository.save(milestone);
                    return ResponseEntity.ok(milestone);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMilestone(@PathVariable("id") Long id) {
        return milestoneRepository.findById(id)
                .map(milestone -> {
                    milestoneRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}