package com.axon.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserMilestoneKey implements Serializable {

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_milestone")
    private Long idMilestone;

    public UserMilestoneKey() {}

    public UserMilestoneKey(Long idUser, Long idMilestone) {
        this.idUser = idUser;
        this.idMilestone = idMilestone;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdMilestone() {
        return idMilestone;
    }

    public void setIdMilestone(Long idMilestone) {
        this.idMilestone = idMilestone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMilestoneKey that = (UserMilestoneKey) o;
        return Objects.equals(idUser, that.idUser) &&
               Objects.equals(idMilestone, that.idMilestone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idMilestone);
    }
}

