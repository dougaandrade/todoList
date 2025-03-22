package com.ignis.to_do.service;

import org.springframework.stereotype.Service;
import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.model.Notification;
import com.ignis.to_do.model.User;

@Service
public class NotificationService {
   private TaskService taskService;

   public NotificationService(TaskService taskService) {
          this.taskService = taskService;
   }

   public void createNotification(TaskDTO task , User user){
          //TO DO

   }
   
   public void sendNotification(Notification notification, User user){
        //TO DO
   }

   public void getNotificationsByUser(User user){
         //TO DO
   }

   public void deleteNotification(Notification notification){

   }

   public String getTasksToNotify(){
        
        Iterable<TaskDTO> allOverdueTasks = taskService.checkAllOverdueTasks();
        allOverdueTasks.forEach(task -> System.out.println(task.getTitle()));
        
        return allOverdueTasks.toString();

   }

   public void markNotificationAsRead(Notification notification){
        notification.setRead(true);
   }

}
