package com.axon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "milestones")
public class Milestone {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_milestone")
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "obtention")
    private String obtention;
    
    @Column(name = "reward")
    private String reward;
    
    @Column(name = "progress")
    private String progress;
    
    public Milestone() {}

    public Milestone(Long id, String title, String obtention, String reward, String progress) {
        this.id = id;
        this.title = title;
        this.obtention = obtention;
        this.reward = reward;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObtention() {
        return obtention;
    }

    public void setObtention(String obtention) {
        this.obtention = obtention;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
