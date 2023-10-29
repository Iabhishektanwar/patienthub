package com.hospital.patienthub.patienthub.Exceptions;

public class InvalidIDException extends Exception {

    public InvalidIDException() {
        super();
    }

    public InvalidIDException(String message) {
        super(message);
    }

    public InvalidIDException(String s, NumberFormatException e) {
    }
}
