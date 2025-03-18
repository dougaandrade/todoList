package com.ignis.to_do.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.Validator.StatusValidator;
import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskRepository;

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
    
    @Override
    public void checkOverdueTasks(Long taskId) {
        
        Task task = taskRepository.findById(taskId).get();   
        Date dueDate = task.getDueDate();

        if (dueDate.before(new Date())) {            
            sendTaskReminder();            
        }

        System.out.println("Verificar tarefas atrasadas");        
    }

    public void sendTaskReminder() {        
    }
}
