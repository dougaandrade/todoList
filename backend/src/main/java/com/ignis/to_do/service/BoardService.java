package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.model.Board;
import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service    
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserService userService;    

    public BoardDTO createBoard(String title, Long ownerId) {

        User user = userService.getUser(ownerId);   
        Board board = new Board(title, user);
        boardRepository.save(board);
        return new BoardDTO(board.getId(), board.getTitle(),
            board.getOwner().getId());
    }

    public BoardDTO getBoardDTO(Long id) {

        Board board = boardRepository.findById(id).get();
        return new BoardDTO(board.getId(), board.getTitle(), 
            board.getOwner().getId());
    }

    public Iterable<BoardDTO> getAllBoards() {
        
        return boardRepository.findAll().stream().map(board -> new BoardDTO(
            board.getId(), board.getTitle(), board.getOwner().getId())).toList();
    }

    public Iterable<BoardDTO> getMyBoards(Long ownerId) {
        User user = userService.getUser(ownerId);  
        return user.getBoards().stream().map(board -> new BoardDTO(board.getId(),
             board.getTitle(), board.getOwner().getId())).toList();
    }

    public Iterable<TaskListDTO> myTasksListsByBoard(Long id){

        Board board = boardRepository.findById(id).get();
        return board.getTaskLists().stream().map(taskList -> new TaskListDTO(
            taskList.getId(), taskList.getName(), taskList.getBoard().getId())).toList();
    }

    public boolean isFavorite(Long boardId) {

        Boolean boardIsFavorite = boardRepository.findById(boardId).get().isFavorite();
        return boardIsFavorite;
    }

    @Transactional
    public void toggleFavorite(Long boardId) {

        Boolean board = boardRepository.findById(boardId).get().isFavorite();
        boardRepository.updateFavorite(boardId, !board);
    }
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public BoardDTO updateBoard(Long id, String title) {
        boardRepository.updateTitle(id, title);
        return new BoardDTO(id, title, boardRepository.findById(id).get().getOwner().getId());
    }

    
    public Board getBoard(Long id) {
        return boardRepository.findById(id).get();
    }
}
