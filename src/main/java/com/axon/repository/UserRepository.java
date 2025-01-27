package com.axon.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.axon.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//Personalized Methods
    Optional<User> findByUsername(String username);
}
