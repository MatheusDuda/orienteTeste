package com.oriente.entity;

import com.oriente.entity.enums.ProjectRole;
import jakarta.persistence.*;

@Entity
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    public ProjectMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_in_project", nullable = false)
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