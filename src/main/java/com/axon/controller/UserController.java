package com.axon.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.model.User;
import com.axon.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
	    Optional<User> user = userRepository.findByUsername(username);
	    
	    if (user.isPresent()) {
	        return ResponseEntity.ok(user.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails){
		Optional<User> optionalUser = userRepository.findById(id);
		
		if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setLevel(userDetails.getLevel());
            user.setExperience(userDetails.getExperience());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
