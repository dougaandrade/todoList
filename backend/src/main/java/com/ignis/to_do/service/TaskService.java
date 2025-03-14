package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskListService taskListService;

    public TaskDTO createTask(String title, Long taskListId) {
        TaskList taskList = taskListService.getList(taskListId);
        Task task = new Task(title, taskList);
        taskRepository.save(task);
        return new TaskDTO(task.getId(), title, task.getStatus(), taskListId);
    }
    public Task createTask(Task task) {        
        return taskRepository.save(task);
    }

    public TaskDTO getTaskById(Long taskId) {  
        Task task = taskRepository.findById(taskId).get();      
        return new TaskDTO(task.getId(), task.getTitle(), task.getStatus(), task.getList().getId()); 
    }

    public Iterable<TaskDTO> getAllTasks() {


        return taskRepository.findAll().stream().map(task -> new TaskDTO(task.getId(),
            task.getTitle(), task.getStatus(), task.getList().getId())).toList();
    }
    
    public void deleteTaskById(Long taskId) {        
        taskRepository.deleteById(taskId);     
    }
    @Transactional
    public TaskDTO updateTaskTitle(Long taskListId, String title) {
        taskRepository.updateTaskTitle(taskListId, title);        
        return new TaskDTO(taskListId, title, taskRepository.findById(taskListId).get().getStatus(),
         taskRepository.findById(taskListId).get().getList().getId());
    }
    
}
