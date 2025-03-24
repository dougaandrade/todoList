package com.ignis.to_do.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Notification {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private Task task;
    private String message;      
    private boolean read = false;   

    public Notification(User user, Task task, String message, boolean read) {

        this.user = user;
        this.task = task;
        this.message = message;
        this.read = read;
        
    }

}
 