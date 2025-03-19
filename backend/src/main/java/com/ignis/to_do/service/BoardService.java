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

    public BoardDTO createBoard(BoardDTO boardDTO) {

        User user = userService.getUser(boardDTO.getOwnerId());   
        Board board = new Board(boardDTO.getTitle(), user);
        boardRepository.save(board);
        return new BoardDTO(board.getId(), board.getTitle(),
            board.getOwner().getId());
    }

    public BoardDTO getBoardById(Long boardId) {

        Board board = boardRepository.findById(boardId).get();
        return new BoardDTO(board.getId(), board.getTitle(), 
            board.getOwner().getId());
    }

    public Iterable<BoardDTO> getAllBoards() {
        
        return boardRepository.findAll().stream().map(board -> new BoardDTO(
            board.getId(), board.getTitle(), board.getOwner().getId())).toList();
    }

    public Iterable<BoardDTO> getMyBoardsByOwnerId(Long ownerId) {

        User user = userService.getUser(ownerId);  
        return user.getBoards().stream().map(board -> new BoardDTO(board.getId(),
             board.getTitle(), board.getOwner().getId())).toList();
    }

    public Iterable<TaskListDTO> myTasksListsByBoardId(Long boardId){

        Board board = boardRepository.findById(boardId).get();
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
    public void deleteBoardById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public BoardDTO updateBoardTitle(BoardDTO boardDTO) {
        boardRepository.updateTitle(boardDTO.getId(), boardDTO.getTitle());
        Long boardId = boardDTO.getId();
        return new BoardDTO(boardId, boardDTO.getTitle(), boardRepository.findById(boardId).get().getOwner().getId());
    }

    
    public Board getBoard(Long id) {
        return boardRepository.findById(id).get();
    }
}
