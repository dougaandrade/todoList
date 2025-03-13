package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.service.BoardService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/board")
@Tag(name = "Board Controller", description = "Gerenciamento de Boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/createBoard/{title}/{ownerId}")
    public BoardDTO createBoard(
        @PathVariable String title,
        @PathVariable Long ownerId
        ){

        return boardService.createBoard(title, ownerId);
    }
    
    @GetMapping("/getBoard/{id}")
    public BoardDTO getBoardDTO(@PathVariable Long id) {
        
        return boardService.getBoardDTO(id);
    }   

    @GetMapping("/allBoards")
    public Iterable<BoardDTO> getAllBoards() {

        return boardService.getAllBoards();
    }

    @GetMapping("myBoards/{id}")
    public Iterable<BoardDTO> getMyBoards(@PathVariable long id) {
        
        return boardService.getMyBoards(id);
    }

    @GetMapping("/isFavorite/{id}")
    public Boolean isFavorite(@PathVariable Long id) {  

        return boardService.isFavorite(id);
    }

    @PutMapping("/toggleFavorite/{id}")
    public void toggleFavorite(@PathVariable Long id) {
        boardService.toggleFavorite(id);
    }

    @GetMapping("myTasksByBoard/{id}")
    public Iterable<TaskListDTO> myTasksListsByBoard(@PathVariable Long id){
        return boardService.myTasksListsByBoard(id);
    }

    @DeleteMapping("/deleteBoard/{id}")
    public void deleteBoard(@PathVariable Long id) {

        boardService.deleteBoard(id);
    }

    @PutMapping("/updateBoard/{id}/{title}")
    public BoardDTO updateBoard(
        @PathVariable Long id,
        @PathVariable String title) {
            
        return boardService.updateBoard(id, title);
    }

    
}
