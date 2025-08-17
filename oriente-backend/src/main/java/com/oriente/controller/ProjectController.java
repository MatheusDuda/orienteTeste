package com.oriente.controller;

import com.oriente.entity.Project;
import com.oriente.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000") // Para funcionar com React
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // GET /api/projects - Listar todos os projetos
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // GET /api/projects/{id} - Buscar projeto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return ResponseEntity.ok(project.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/projects - Criar novo projeto
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // PUT /api/projects/{id} - Atualizar projeto
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            // owner não é atualizado aqui por segurança
            return ResponseEntity.ok(projectRepository.save(project));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/projects/{id} - Deletar projeto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/projects/count - Contar projetos (para teste)
    @GetMapping("/count")
    public long countProjects() {
        return projectRepository.count();
    }
}