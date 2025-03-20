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
    
    // VERIFICAR SE PRECISA SER private final
    @Autowired
    private UserRepository userRepository;


    public UserDTO createUser(UserDTO userDTO){

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
        .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + userId + " não encontrado"));;
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public Iterable<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> new UserDTO(user.getId(),
            user.getName(), user.getEmail(),  user.getPassword())).toList();
    }

    
    @Transactional
    public UserDTO updateUserById(UserDTO userDTO) {
        userRepository.updateUser(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        return new UserDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
    }



    public void deleteUserById(Long userId){ 
        User user = userRepository.findById(userId).get();
        userRepository.delete(user);
    }

    @Transactional
    public void updatePasswordById(UserDTO userDTO) {
        userRepository.updatePasswordById(userDTO.getId(), userDTO.getPassword());
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
