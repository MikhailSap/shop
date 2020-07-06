package ru.sap.exception;

public class IdNotNullException extends RuntimeException {
    private final String MESSAGE = "Id not allowed for create method.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
