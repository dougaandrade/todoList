package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/createBoard/{title}/{ownerId}")
    public BoardDTO createBoard(
        @PathVariable String title,
        @PathVariable Long ownerId
        ) {

        boardService.createBoard(title, ownerId);
        return null;
    }
    
    @GetMapping("/getBoard/{id}")
    public BoardDTO getBoardDTO(@PathVariable Long id) {
        
        return boardService.getBoardDTO(id);
    }   

}
