package com.ignis.to_do.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.exception.BoardException.BoardAlreadyExistsException;
import com.ignis.to_do.exception.BoardException.BoardNotFoundException;
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

        Optional<Board> existingBoard = boardRepository.findByTitle(boardDTO.getTitle());
        if (existingBoard.isPresent()) {
            throw new BoardAlreadyExistsException("Board com nome '" + boardDTO.getTitle() + "' já existe.");
        }

        User user = userService.getUser(boardDTO.getOwnerId());   
        Board board = new Board(boardDTO.getTitle(), user);
        boardRepository.save(board);
        return new BoardDTO(board.getId(), board.getTitle(),
            board.getOwner().getId());
    }

    public BoardDTO getBoardById(Long boardId) {

        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException("Board com ID " + boardId + " não encontrado"));
        return new BoardDTO(board.getId(), board.getTitle(), 
            board.getOwner().getId());
    }

    public void verifyBoard(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException("Board com ID " + boardId + " não encontrado"));
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

        verifyBoard(boardId);
        Boolean boardIsFavorite = boardRepository.findById(boardId).get().isFavorite();
        return boardIsFavorite;
    }

    @Transactional
    public void toggleFavorite(Long boardId) {
        
        verifyBoard(boardId);
        Boolean board = boardRepository.findById(boardId).get().isFavorite();
        boardRepository.updateFavorite(boardId, !board);
    }
    public void deleteBoardById(Long boardId) {
        verifyBoard(boardId);
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public BoardDTO updateBoardTitle(BoardDTO boardDTO) {

        verifyBoard(boardDTO.getId());
        boardRepository.updateTitle(boardDTO.getId(), boardDTO.getTitle());
        Long boardId = boardDTO.getId();
        return new BoardDTO(boardId, boardDTO.getTitle(), boardRepository.findById(boardId).get().getOwner().getId());
    }

    
    public Board getBoard(Long id) {
        return boardRepository.findById(id).get();
    }
}
