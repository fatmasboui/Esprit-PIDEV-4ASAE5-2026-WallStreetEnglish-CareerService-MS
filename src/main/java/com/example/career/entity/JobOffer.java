package com.example.career.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Level requiredLevel; // <-- ici on utilise l'enum correctement

    private boolean active;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Application> applications;

    public JobOffer() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Level getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(Level requiredLevel) { this.requiredLevel = requiredLevel; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}