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
    
    @GetMapping("/getBoard/{boardId}")
    public BoardDTO getBoardDTO(@PathVariable Long boardId) {
        
        return boardService.getBoardById(boardId);
    }   

    @GetMapping("/allBoards")
    public Iterable<BoardDTO> getAllBoards() {

        return boardService.getAllBoards();
    }

    @GetMapping("myBoards/{boardId}")
    public Iterable<BoardDTO> getMyBoardsByOwnerId(@PathVariable Long boardId) {
        
        return boardService.getMyBoardsByOwnerId(boardId);
    }

    @GetMapping("/isFavorite/{boardId}")
    public Boolean isFavorite(@PathVariable Long boardId) {  

        return boardService.isFavorite(boardId);
    }

    @GetMapping("myTasksByBoard/{boardId}")
    public Iterable<TaskListDTO> myTasksListsByBoard(@PathVariable Long boardId){
        return boardService.myTasksListsByBoardId(boardId);
    }

    @PutMapping("/updateBoardTitle/{boardId}/{title}")
    public BoardDTO updateBoardTitle(
        @PathVariable Long boardId,
        @PathVariable String title) {
            
        return boardService.updateBoardTitle(boardId, title);
    }

    @PutMapping("/toggleFavorite/{boardId}")
    public void toggleFavorite(@PathVariable Long boardId) {
        boardService.toggleFavorite(boardId);
    }
    
    @DeleteMapping("/deleteBoard/{boardId}")
    public void deleteBoardById(@PathVariable Long boardId) {

        boardService.deleteBoardById(boardId);
    }
}
