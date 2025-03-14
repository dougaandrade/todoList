package com.ignis.to_do.dto;

import lombok.Data;

import com.ignis.to_do.model.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private TaskStatus status;
    private Long listId;
}
