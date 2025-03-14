package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.UserDTO;
import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    // VERIFICAR SE PRECISA SER private final
    @Autowired
    private UserRepository userRepository;


    public UserDTO createUser(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getEmail());
        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserDTO getUserDTOById(Long userId){
        User user = userRepository.findById(userId).get();
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public Iterable<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),
            user.getName(), user.getEmail())).toList();
    }

    
    @Transactional
    public UserDTO updateUserById(long userId, UserDTO userDTO) {
        userRepository.updateUser(userId, userDTO.getName(), userDTO.getEmail());
        return new UserDTO(userId, userDTO.getName(), userDTO.getEmail());
    }


    public void deleteUserById(Long userId){ 
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    public boolean validateUser(String name, String email){
        return userRepository.existsByNameAndEmail(name, email);
    }  

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
