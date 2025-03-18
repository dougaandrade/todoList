package com.ignis.to_do.service;

public interface TaskReminder {
    
    public void checkOverdueTasks(Long taskId);
    public void sendTaskReminder();


}
