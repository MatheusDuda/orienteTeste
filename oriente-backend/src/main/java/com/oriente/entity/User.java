package com.oriente.entity;

import com.oriente.entity.enums.UserRole;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.persistence.Column(nullable = false)
    private String name;

    @jakarta.persistence.Column(nullable = false, unique = true)
    private String email;

    @jakarta.persistence.Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @jakarta.persistence.Column(nullable = false)
    private UserRole role;

    // Relacionamentos
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Project> ownedProjects = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMemberships = new ArrayList<>();

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatMessage> sentMessages = new ArrayList<>();

    // Construtores
    public User() {}

    public User(String name, String email, String passwordHash, UserRole role) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Project> getOwnedProjects() {
        return ownedProjects;
    }

    public void setOwnedProjects(List<Project> ownedProjects) {
        this.ownedProjects = ownedProjects;
    }

    public List<ProjectMember> getProjectMemberships() {
        return projectMemberships;
    }

    public void setProjectMemberships(List<ProjectMember> projectMemberships) {
        this.projectMemberships = projectMemberships;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public List<ChatMessage> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<ChatMessage> sentMessages) {
        this.sentMessages = sentMessages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}