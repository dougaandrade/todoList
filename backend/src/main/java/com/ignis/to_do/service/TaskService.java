package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.model.Task;
import com.ignis.to_do.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {        
        return taskRepository.save(task);
    }
    
    public void deleteTask(Task task) {        
        taskRepository.delete(task);        
    }

    
}
