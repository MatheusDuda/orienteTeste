package com.oriente.controller;

import com.oriente.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    @Autowired
    private KanbanColumnRepository kanbanColumnRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Oriente Backend está funcionando!";
    }

    @GetMapping("/count")
    public String getEntityCounts() {
        return String.format(
                "Usuários: %d, Projetos: %d, Colunas: %d, Tarefas: %d, Mensagens: %d, Membros: %d",
                userRepository.count(),
                projectRepository.count(),
                kanbanColumnRepository.count(),
                taskRepository.count(),
                chatMessageRepository.count(),
                projectMemberRepository.count()
        );
    }
}