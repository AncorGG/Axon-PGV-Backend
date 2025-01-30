package com.axon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axon.model.Badge;

public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
