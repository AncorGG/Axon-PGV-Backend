package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>{

}
