package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ignis.to_do.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long>{
    
}