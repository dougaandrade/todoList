package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskListRepository;

@Service
public class TaskListService {
    @Autowired
    private TaskListRepository taskListRepository;
    
    public TaskList createTaskList(TaskList taskList) {        
        return taskListRepository.save(taskList);
    }

    public void deleteTaskList(TaskList taskList) {        
        taskListRepository.delete(taskList);        
    }

}
