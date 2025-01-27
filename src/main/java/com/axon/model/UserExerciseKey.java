package com.axon.model;

	import java.io.Serializable;
	import java.util.Objects;

	import jakarta.persistence.Column;
	import jakarta.persistence.Embeddable;

@Embeddable
public class UserExerciseKey implements Serializable{

	@Column(name = "id_user")
	private Long idUser;
		
	@Column(name = "id_exercise")
	private Long idExercise;
		
	public UserExerciseKey() {}
		
	public UserExerciseKey(Long id_user, Long id_exercise) {
		this.idUser = id_user;
		this.idExercise = id_exercise;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdExercise() {
		return idExercise;
	}

	public void setIdExercise(Long idExercise) {
		this.idExercise = idExercise;
	}
		
	@Override
	   public boolean equals(Object o) {
	       if (this == o) return true;
	       if (o == null || getClass() != o.getClass()) return false;
	       UserExerciseKey that = (UserExerciseKey) o;
	       return Objects.equals(idUser, that.idUser) &&
	              Objects.equals(idExercise, that.idExercise);
	   }

	   @Override
	   public int hashCode() {
	       return Objects.hash(idUser, idExercise);
	   }
		
}

	

