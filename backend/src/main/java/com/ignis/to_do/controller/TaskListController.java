package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.service.TaskListService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/taskList")
// @AllArgsConstructor Analisar a necesseidade para evitar o @Autowired
// @NoArgsConstructor
@Tag(name = "TaskList Controller", description = "Gerenciamento de listas de tarefas")
public class TaskListController {
    
    @Autowired
    TaskListService taskListService;
    
    @PostMapping("/createTaskList/{title}/{boardId}")
    public TaskListDTO createTaskList(
        @PathVariable String title, 
        @PathVariable Long boardId) {      

        return taskListService.createTaskList(title, boardId);
    }

    @GetMapping("/{taskListId}")
    public TaskListDTO getTaskListById(@PathVariable Long taskListId) {

        return taskListService.getTaskListById(taskListId);
    }

    @GetMapping("/allTaskLists")
    public Iterable<TaskListDTO> getAllTaskLists() {

        return taskListService.getAllTaskLists();
    }
    
    @PutMapping("/updateTaskList/{taskListId}/{title}")     
    public TaskListDTO updateTaskListTitle(
        @PathVariable Long taskListId, 
        @PathVariable String title){

        return taskListService.updateTaskListTitle(taskListId, title);
    }

    @PutMapping("updateBoardId/{taskListId}/{boardId}")
    public TaskListDTO updateBoardId(
        @PathVariable Long taskListId,
        @PathVariable Long boardId) {
        
        return taskListService.updateBoardId(taskListId, boardId);
    }

    @DeleteMapping("/deleteTaskList/{taskListId}")
    public void deleteTaskListById(@PathVariable Long taskListId) {

        taskListService.deleteTaskListById(taskListId);
    }
}
