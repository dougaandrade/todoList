package com.ignis.to_do.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; 
    @ManyToOne
    @JoinColumn(name = "board_id")       
    private Board board;
    @OneToMany(mappedBy = "list")
    private List<Task> tasks;

    public Task addTask(Task task) {
        tasks.add(task); 
        return task;
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
    

}
