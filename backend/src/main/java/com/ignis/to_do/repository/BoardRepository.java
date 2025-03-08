package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ignis.to_do.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    
}