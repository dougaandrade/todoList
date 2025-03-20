package com.ignis.to_do.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.exception.TaskListException.TaskListAlreadyExistsException;
import com.ignis.to_do.exception.TaskListException.TaskListNotFoundException;
import com.ignis.to_do.model.Board;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskListRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;
    @Autowired
    private BoardService boardService;
    
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {

        Optional<TaskList> existingList = taskListRepository.findByName(taskListDTO.getName());
        if (existingList.isPresent()) {
            throw new TaskListAlreadyExistsException("TaskList com nome '" + taskListDTO.getName() + "' já existe.");
        }

        Board board = boardService.getBoard(taskListDTO.getBoardId());
        TaskList taskList = new TaskList(taskListDTO.getName(), board);
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    public TaskListDTO getTaskListById(Long taskLitsId) {
        TaskList taskList = taskListRepository.findById(taskLitsId).orElseThrow(() -> 
        new TaskListNotFoundException("TaskList com ID " + taskLitsId + " não encontrada"));

        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    public void verifiyTaskList(Long taskListId) {
        taskListRepository.findById(taskListId).orElseThrow(() -> 
        new TaskListNotFoundException("TaskList com ID " + taskListId + " não encontrada"));
    }

    public Iterable<TaskListDTO> getAllTaskLists() {
        return taskListRepository.findAll().stream().map(taskList -> new TaskListDTO(
            taskList.getId(), taskList.getName(), taskList.getBoard().getId())).toList();
    }

    public TaskList getList(Long id) {
        return taskListRepository.findById(id).get();        
    }

    public void deleteTaskList(TaskList taskList) {     
        verifiyTaskList(taskList.getId());   
        taskListRepository.delete(taskList);        
    }

    public void deleteTaskListById(Long taskListId) {
        verifiyTaskList(taskListId);   
        taskListRepository.deleteById(taskListId);
    }

    public TaskListDTO updateTaskListTitle(TaskListDTO taskListDTO) {
        verifiyTaskList(taskListDTO.getId());
        TaskList taskList = taskListRepository.findById(taskListDTO.getId()).get();
        taskList.setName(taskListDTO.getName());
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
                                taskList.getBoard().getId());
    }

    @Transactional
    public TaskListDTO updateBoardId(TaskListDTO taskListDTO) {
        verifiyTaskList(taskListDTO.getId());
        Long id = taskListDTO.getId();
        taskListRepository.updateBoardId(taskListDTO.getId(), taskListDTO.getBoardId());
        return new TaskListDTO(id, taskListRepository.findById(id).get().getName(),
                                taskListDTO.getBoardId());

    }

}
