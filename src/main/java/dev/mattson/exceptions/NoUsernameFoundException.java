package dev.mattson.exceptions;

public class NoUsernameFoundException extends RuntimeException{

    public NoUsernameFoundException(String message) {
        super(message);
    }
}
