package com.axon.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_exercise")
public class UserExercise {

	@EmbeddedId
	private UserExerciseKey id;
	
	@ManyToOne
	@MapsId("idUser")
	@JoinColumn(name = "id_user")
	private User user;
	
	@ManyToOne
	@MapsId("idExercise")
	@JoinColumn(name = "id_exercise")
	private Exercise exercise;
	
	@Column(name = "completition_date")
	private LocalDateTime completition_date;
	
	public UserExercise() {}

	public UserExerciseKey getId() {
		return id;
	}

	public void setId(UserExerciseKey id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public LocalDateTime getCompletition_date() {
		return completition_date;
	}

	public void setCompletition_date(LocalDateTime completition_date) {
		this.completition_date = completition_date;
	}
		
}
