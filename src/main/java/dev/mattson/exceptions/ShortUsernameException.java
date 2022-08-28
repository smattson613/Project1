package dev.mattson.exceptions;

public class ShortUsernameException extends RuntimeException{

    public ShortUsernameException(String message) {
        super(message);
    }
}
