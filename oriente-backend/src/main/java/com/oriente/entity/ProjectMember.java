package com.oriente.entity;

import com.oriente.entity.enums.ProjectRole;
import jakarta.persistence.*;

@Entity
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    private ProjectMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @jakarta.persistence.Column(name = "role_in_project", nullable = false)
    private ProjectRole roleInProject;

    // Construtores
    public ProjectMember() {}

    public ProjectMember(User user, Project project, ProjectRole roleInProject) {
        this.user = user;
        this.project = project;
        this.roleInProject = roleInProject;
        this.id = new ProjectMemberId(user.getId(), project.getId());
    }

    // Getters e Setters
    public ProjectMemberId getId() {
        return id;
    }

    public void setId(ProjectMemberId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (id == null) {
            id = new ProjectMemberId();
        }
        id.setUserId(user.getId());
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
        if (id == null) {
            id = new ProjectMemberId();
        }
        id.setProjectId(project.getId());
    }

    public ProjectRole getRoleInProject() {
        return roleInProject;
    }

    public void setRoleInProject(ProjectRole roleInProject) {
        this.roleInProject = roleInProject;
    }
}

// Classe para chave composta
@Embeddable
class ProjectMemberId {

    @jakarta.persistence.Column(name = "user_id")
    private Long userId;

    @jakarta.persistence.Column(name = "project_id")
    private Long projectId;

    public ProjectMemberId() {}

    public ProjectMemberId(Long userId, Long projectId) {
        this.userId = userId;
        this.projectId = projectId;
    }

    // Getters e Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectMemberId)) return false;
        ProjectMemberId that = (ProjectMemberId) o;
        return userId.equals(that.userId) && projectId.equals(that.projectId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + projectId.hashCode();
    }
}