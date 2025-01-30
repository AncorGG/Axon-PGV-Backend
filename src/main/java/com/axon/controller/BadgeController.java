package com.axon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axon.model.Badge;
import com.axon.repository.BadgeRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/badges")
public class BadgeController {

	@Autowired
	private BadgeRepository badgeRepository;
	
	@GetMapping
	public List<Badge> getBadges() {
		return badgeRepository.findAll();
	}
}
