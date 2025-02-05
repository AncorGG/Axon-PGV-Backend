package com.axon.model;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "routines")
public class Routine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_routine")
	private Long id;
	
	@Column(name = "routine_name")
    @JsonProperty("routine_name")
    private String routine_name;
	
	@Column(name = "routine_description")
	private String routine_description;
	
	@Column(name = "creation_date")
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private User user;
	
	public Routine() {}


	public Long getId_routine() {
		return id;
	}


	public void setId_routine(Long id_routine) {
		this.id = id_routine;
	}


	 @JsonProperty("routine_name")
	    public String getRoutine_name() {
	        return routine_name;
	    }

	    public void setRoutine_name(String routine_name) {
	        this.routine_name = routine_name;
	    }


	public String getDescription() {
		return routine_description;
	}


	public void setDescription(String routine_description) {
		this.routine_description = routine_description;
	}


	public LocalDateTime getCreation_date() {
		return creation_date;
	}


	public void setCreation_date(LocalDateTime creation_date) {
		this.creation_date = creation_date;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
}