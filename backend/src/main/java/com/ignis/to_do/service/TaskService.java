package com.ignis.to_do.service;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.exception.TasksException.TaskNotFoundException;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskRepository;
import com.ignis.to_do.validator.StatusValidator;

import jakarta.transaction.Transactional;

@Service
public class TaskService implements TaskReminder {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskListService taskListService;

    public String createTask(TaskDTO taskDTO) {  

        StatusValidator taskStatus = new StatusValidator(taskDTO.getStatus());
        taskStatus.validateStatus(taskDTO.getStatus());

        if (taskStatus.validateStatus(taskDTO.getStatus())) {            
            TaskList taskList = taskListService.getList(taskDTO.getListId());        
            Task task = new Task(taskDTO.getTitle(), taskList,taskDTO.getStatus());      
            taskRepository.save(task);        
            return "Task criada com sucesso";
        }

        return "Status inválido";
    }

    public TaskDTO getTaskById(Long taskId) {  
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));    
        return new TaskDTO(task.getId(), task.getTitle(), task.getStatus(), task.getList().getId()); 
    }

    public void verifyTask(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    public Iterable<TaskDTO> getAllTasks() {

        return taskRepository.findAll().stream().map(task -> new TaskDTO(task.getId(),
            task.getTitle(), task.getStatus(), task.getList().getId())).toList();
    }
    
    public void deleteTaskById(Long taskId) { 
        verifyTask(taskId);       
        taskRepository.deleteById(taskId);     
    }

    @Transactional
    public TaskDTO updateTaskTitle(TaskDTO taskDTO) {

        verifyTask(taskDTO.getId());
        String title = taskDTO.getTitle();
        Long taskListId = taskDTO.getId(); 
        taskRepository.updateTaskTitle(taskListId, title); 

        return new TaskDTO(taskListId, title, taskRepository.findById(taskListId).get().getStatus(),
        taskRepository.findById(taskListId).get().getList().getId());
    }
    
    @Override
    public String checkOverdueTasks(Long taskId) {
        
        verifyTask(taskId);
        Task task = taskRepository.findById(taskId).get();
        LocalDate today = LocalDate.now();
        LocalDate taskDueDate = task.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (taskDueDate.isBefore(today)) {
            sendTaskReminder();
            return "Lembrete de tarefa enviado.";
        } 
        
        return "A tarefa ainda não está atrasada.";
 
    }

    @Override
    public void sendTaskReminder() {        
    }
}
