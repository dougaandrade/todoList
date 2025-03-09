package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ignis.to_do.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
    
}