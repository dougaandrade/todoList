package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.UserDTO;
import com.ignis.to_do.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Gerenciamento de Usuários")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        
        return userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        
        boolean isValid = userService.validateUser(
            userDTO.getName(), userDTO.getEmail(), userDTO.getPassword());
        
        if (isValid) {
            return ResponseEntity.ok("Logado com sucesso");
        } 
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            "Usuário não encontrado");
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        
        return userService.getUserDTOById(userId);
    }

    @GetMapping("/all")
    public Iterable<UserDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @PutMapping("/updateUser")
    public UserDTO updateUserById(
        @RequestBody UserDTO userDTO) {

        return userService.updateUserById(userDTO);
    }

    @PutMapping("/updatePassword")
    public void updatePasswordById(
        @RequestBody UserDTO userDTO) {
        
        userService.updatePasswordById(userDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        
        userService.deleteUserById(userId);
    }
}
