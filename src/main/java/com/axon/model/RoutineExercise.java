package com.axon.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "routine_exercise")
public class RoutineExercise {

	@EmbeddedId
	private RoutineExerciseKey id;
	
	@ManyToOne
    @MapsId("idRoutine")
    @JoinColumn(name = "id_routine")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Routine routine;
	
	@ManyToOne
	@MapsId("idExercise")
	@JoinColumn(name = "id_exercise")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Exercise exercise;
	
	@Column(name = "sequence_order")
	private Integer sequence_order;

	public RoutineExercise() {}

	public RoutineExerciseKey getId() {
		return id;
	}

	public void setId(RoutineExerciseKey id) {
		this.id = id;
	}

	public Routine getRoutine() {
		return routine;
	}

	public void setRoutine(Routine routine) {
		this.routine = routine;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

	public Integer getSequence_order() {
		return sequence_order;
	}

	public void setSequence_order(Integer sequence_order) {
		this.sequence_order = sequence_order;
	}
	
	
	
}
