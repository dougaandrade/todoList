package com.ignis.to_do.exception.TasksException;
import com.ignis.to_do.exception.GlobalException;

public class TaskNotFoundException extends GlobalException {
    public TaskNotFoundException(Long id) {
        super("Tarefa com ID " + id + " não encontrada.");
    }
}
