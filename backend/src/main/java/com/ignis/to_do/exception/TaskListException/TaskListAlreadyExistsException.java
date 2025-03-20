package com.ignis.to_do.exception.TaskListException;

public class TaskListAlreadyExistsException extends RuntimeException {
    public TaskListAlreadyExistsException(String message) {
        super(message);
    }
}
