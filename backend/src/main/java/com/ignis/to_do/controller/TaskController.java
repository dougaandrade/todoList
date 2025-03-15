package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.service.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
// @AllArgsConstructor Analisar a necesseidade para evitar o @Autowired
// @NoArgsConstructor   
@Tag(name = "Task Controller", description = "Gerenciamento de tarefas")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/createTask")
    public TaskDTO createTask(@Valid @RequestBody TaskDTO taskDTO) {

        return taskService.createTask(taskDTO);
    }
    // @PostMapping("/createTask/{titulo}/{taskListId}")
    // public TaskDTO createTask(
    //     @PathVariable String titulo,
    //     @PathVariable Long taskListId) {

    //     return taskService.createTask(titulo, taskListId);
    // }

    @GetMapping("/getTask/{taskId}")
    public TaskDTO getTaskById(@PathVariable Long taskId) {

        return taskService.getTaskById(taskId);
    }   

    @GetMapping("/getAllTasks")
    public Iterable<TaskDTO> getAllTasks() {

        return taskService.getAllTasks();
    }   
    
    @PutMapping("/updateTask/{taskId}/{title}")
    public TaskDTO updateTaskTitle(
        @PathVariable Long taskId,
        @PathVariable String title) {
            
        return taskService.updateTaskTitle(taskId, title);
    }

    @DeleteMapping("/deleteTask/{taskId}")
    public void deleteTaskById(@PathVariable Long taskId) {
        
        taskService.deleteTaskById(taskId); 
    }
}   
