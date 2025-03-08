package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.UserRepository;

@Service
public class UserService {
    
    // VERIFICAR SE PRECISER private final
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    // ATENCAO: Passar somente o ID
    public void deleteUser(User user){
        userRepository.delete(user);
    }  
}
