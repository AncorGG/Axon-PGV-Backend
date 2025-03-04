package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.UserMilestone;
import com.axon.model.UserMilestoneKey;


public interface UserMilestoneRepository extends JpaRepository<UserMilestone, UserMilestoneKey>{

}
