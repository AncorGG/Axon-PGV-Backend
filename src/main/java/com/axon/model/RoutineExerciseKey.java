package com.axon.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class RoutineExerciseKey implements Serializable {
	
	@Column(name = "id_routine")
    private Long idRoutine;

    @Column(name = "id_exercise")
    private Long idExercise;
	
    public RoutineExerciseKey() {}
	
	public RoutineExerciseKey(Long id_routine, Long id_exercise) {
        this.idRoutine = id_routine;
        this.idExercise = id_exercise;
    }

	public Long getId_routine() {
		return idRoutine;
	}

	public void setId_routine(Long id_routine) {
		this.idRoutine = id_routine;
	}

	public Long getId_exercise() {
		return idExercise;
	}

	public void setId_exercise(Long id_exercise) {
		this.idExercise = id_exercise;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutineExerciseKey that = (RoutineExerciseKey) o;
        return Objects.equals(idRoutine, that.idRoutine) &&
               Objects.equals(idExercise, that.idExercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoutine, idExercise);
    }
	
}
