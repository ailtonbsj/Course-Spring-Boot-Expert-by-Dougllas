package io.github.ailtonbsj.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException() {
        super("Invalid password!");
    }
}
