package com.ignis.to_do.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ignis.to_do.dto.UserDTO;
import com.ignis.to_do.exception.UserException.UserAlreadyExistsException;
import com.ignis.to_do.exception.UserException.UserNotFoundException;
import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;


    public UserDTO createUser(UserDTO userDTO){

        if ((userDTO.getId()) != null) {
            verifyUser(userDTO.getId());
        }
        
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("Usuário com email " + userDTO.getEmail() + " já existe.");
        }

        User user = new User(userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        user = userRepository.save(user);
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());

    }

    public UserDTO getUserDTOById(Long userId){
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + userId + " não encontrado"));
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public void verifyUser(Long userId){
        userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + userId + " não encontrado"));
    }

    public Iterable<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),
            user.getName(), user.getEmail(),  user.getPassword())).toList();
    }

    
    @Transactional
    public UserDTO updateUserById(UserDTO userDTO) {

        verifyUser(userDTO.getId());
        userRepository.updateUser(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return new UserDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }



    public void deleteUserById(Long userId){ 

        verifyUser(userId);
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Transactional
    public void updatePasswordById(UserDTO userDTO) {

        verifyUser(userDTO.getId());
        userRepository.updatePasswordById(userDTO.getId(), userDTO.getPassword());
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
