package ru.sap.exception;

public class NoSuchUserException extends RuntimeException {
    private final String MESSAGE = "No found such user.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
