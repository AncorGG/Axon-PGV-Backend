package com.axon.model;


import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "routines")
public class Routine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_routine;
	
	@Column(name = "routine_name")
	private String routine_name;
	
	@Column(name = "routine_description")
	private String routine_description;
	
	@Column(name = "creation_date")
    @CreationTimestamp
	private LocalDate creation_date;
	
	@Column(name = "id_user")
	private Integer id_user;
	
	
	public Routine() {}


	public Long getId_routine() {
		return id_routine;
	}


	public void setId_routine(Long id_routine) {
		this.id_routine = id_routine;
	}


	public String getName() {
		return routine_name;
	}


	public void setName(String routine_name) {
		this.routine_name = routine_name;
	}


	public String getDescription() {
		return routine_description;
	}


	public void setDescription(String routine_description) {
		this.routine_description = routine_description;
	}


	public LocalDate getCreation_date() {
		return creation_date;
	}


	public void setCreation_date(LocalDate creation_date) {
		this.creation_date = creation_date;
	}


	public Integer getId_user() {
		return id_user;
	}


	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}

	
	
}
