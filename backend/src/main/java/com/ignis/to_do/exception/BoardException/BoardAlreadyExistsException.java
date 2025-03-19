package com.ignis.to_do.exception.BoardException;

public class BoardAlreadyExistsException extends RuntimeException {
    public BoardAlreadyExistsException(String message) {
        super(message);
    }
}
