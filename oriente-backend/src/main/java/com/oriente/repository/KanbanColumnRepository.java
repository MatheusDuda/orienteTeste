package com.oriente.repository;

import com.oriente.entity.KanbanColumn;  // ← MUDOU AQUI
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KanbanColumnRepository extends JpaRepository<KanbanColumn, Long> {  // ← MUDOU AQUI
    List<KanbanColumn> findByProjectIdOrderByPosition(Long projectId);  // ← TIPO DE RETORNO MUDOU
}