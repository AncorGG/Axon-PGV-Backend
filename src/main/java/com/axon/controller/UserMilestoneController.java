package com.axon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.axon.model.UserMilestone;
import com.axon.model.UserMilestoneKey;
import com.axon.repository.UserMilestoneRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user-milestones")
public class UserMilestoneController {

    @Autowired
    private UserMilestoneRepository userMilestoneRepository;

    @GetMapping
    public List<UserMilestone> getAllUserMilestones() {
        return userMilestoneRepository.findAll();
    }

    @GetMapping("/{idUser}/{idMilestone}")
    public ResponseEntity<UserMilestone> getUserMilestoneById(
            @PathVariable("idUser") Long idUser, 
            @PathVariable("idMilestone") Long idMilestone) {
        
        UserMilestoneKey key = new UserMilestoneKey(idUser, idMilestone);
        Optional<UserMilestone> userMilestone = userMilestoneRepository.findById(key);
        
        return userMilestone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserMilestone> createUserMilestone(@RequestBody UserMilestone userMilestone) {
        UserMilestone createdUserMilestone = userMilestoneRepository.save(userMilestone);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserMilestone);
    }

    @PutMapping("/{idUser}/{idMilestone}")
    public ResponseEntity<UserMilestone> updateUserMilestone(
            @PathVariable("idUser") Long idUser, 
            @PathVariable("idMilestone") Long idMilestone, 
            @RequestBody UserMilestone updatedUserMilestone) {
        
        UserMilestoneKey key = new UserMilestoneKey(idUser, idMilestone);
        return userMilestoneRepository.findById(key)
                .map(userMilestone -> {
                    userMilestone.setCompletionStatus(updatedUserMilestone.getCompletionStatus());
                    userMilestoneRepository.save(userMilestone);
                    return ResponseEntity.ok(userMilestone);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idUser}/{idMilestone}")
    public ResponseEntity<Object> deleteUserMilestone(
            @PathVariable("idUser") Long idUser, 
            @PathVariable("idMilestone") Long idMilestone) {
        
        UserMilestoneKey key = new UserMilestoneKey(idUser, idMilestone);
        return userMilestoneRepository.findById(key)
                .map(userMilestone -> {
                    userMilestoneRepository.deleteById(key);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
