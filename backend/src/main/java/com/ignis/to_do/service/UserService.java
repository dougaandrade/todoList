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
        
        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());

    }

    public UserDTO getUserDTOById(Long userId){
        User user = userRepository.findById(userId).get();
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public Iterable<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),
            user.getName(), user.getEmail(),  user.getPassword())).toList();
    }

    
    @Transactional
    public UserDTO updateUserById(long userId, UserDTO userDTO) {
        userRepository.updateUser(userId, userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return new UserDTO(userId, userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }



    public void deleteUserById(Long userId){ 
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    public boolean validateUser(String name, String email, String password) {
        return userRepository.existsByNameAndEmailAndPassword(name, email, password);
    }  

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
