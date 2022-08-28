package dev.mattson.exceptions;

public class ShortPasswordException extends RuntimeException {

    public ShortPasswordException(String message) {
        super(message);
    }
}