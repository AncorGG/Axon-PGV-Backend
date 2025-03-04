package com.axon.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;

@Entity
@Table(name = "user_milestone")
public class UserMilestone {

    @EmbeddedId
    private UserMilestoneKey id;
    
    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    
    @ManyToOne
    @MapsId("idMilestone")
    @JoinColumn(name = "id_milestone")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Milestone milestone;
    
    @Column(name = "completion_status")
    private Boolean completionStatus;
    
    public UserMilestone() {}

    public UserMilestoneKey getId() {
        return id;
    }

    public void setId(UserMilestoneKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Boolean getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(Boolean completionStatus) {
        this.completionStatus = completionStatus;
    }
}
