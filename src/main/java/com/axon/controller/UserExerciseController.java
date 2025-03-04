package com.axon.controller;

import com.axon.model.UserExercise;
import com.axon.model.UserExerciseKey;
import com.axon.repository.UserExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-exercises")
public class UserExerciseController {

    @Autowired
    private UserExerciseRepository userExerciseRepository;

    @GetMapping
    public List<UserExercise> getAllUserExercises() {
        return userExerciseRepository.findAll();
    }

    @GetMapping("/{userId}/{exerciseId}")
    public ResponseEntity<UserExercise> getUserExerciseById(@PathVariable Long userId, @PathVariable Long exerciseId) {
        UserExerciseKey key = new UserExerciseKey(userId, exerciseId);
        Optional<UserExercise> userExercise = userExerciseRepository.findById(key);
        return userExercise.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserExercise createUserExercise(@RequestBody UserExercise userExercise) {
        return userExerciseRepository.save(userExercise);
    }

    @PutMapping("/{userId}/{exerciseId}")
    public ResponseEntity<UserExercise> updateUserExercise(@PathVariable Long userId, @PathVariable Long exerciseId, @RequestBody UserExercise userExerciseDetails) {
        UserExerciseKey key = new UserExerciseKey(userId, exerciseId);
        Optional<UserExercise> userExerciseOptional = userExerciseRepository.findById(key);

        if (userExerciseOptional.isPresent()) {
            UserExercise userExercise = userExerciseOptional.get();
            userExercise.setCompletition_date(userExerciseDetails.getCompletition_date());
            userExerciseRepository.save(userExercise);
            return ResponseEntity.ok(userExercise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{userId}/{exerciseId}")
    public ResponseEntity<Void> deleteUserExercise(@PathVariable Long userId, @PathVariable Long exerciseId) {
        UserExerciseKey key = new UserExerciseKey(userId, exerciseId);
        if (userExerciseRepository.existsById(key)) {
            userExerciseRepository.deleteById(key);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
