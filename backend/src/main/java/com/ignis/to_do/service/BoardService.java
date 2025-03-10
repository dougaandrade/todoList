package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.BoardDTO;
import com.ignis.to_do.model.Board;
import com.ignis.to_do.model.User;
import com.ignis.to_do.repository.BoardRepository;

@Service    
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserService userService;    

    public Board createBoard(String title, Long ownerId) {
        User user = userService.getUser(ownerId);   
        Board board = new Board(title, user);
        return boardRepository.save(board);
    }

    public BoardDTO getBoardDTO(Long id) {
        Board board = boardRepository.findById(id).get();
        return new BoardDTO(board.getId(), board.getTitle(), board.getOwner().getId());
    }

}
