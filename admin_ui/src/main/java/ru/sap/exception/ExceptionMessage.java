package ru.sap.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ExceptionMessage {
    private Date timestamp;
    private int status;
    private String message;
}
