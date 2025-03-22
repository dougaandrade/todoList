package com.ignis.to_do.model;

import lombok.Data;

@Data
public class Notification {
    
    private User user;
    private String message;      
    private boolean read = false;      
    private Task task;

    public Notification(String message, boolean read, Task task, User user) {

        this.message = message;
        this.read = read;
        this.task = task;
        this.user = user;

    }

}
 