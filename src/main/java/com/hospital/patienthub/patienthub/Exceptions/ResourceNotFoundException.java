package com.hospital.patienthub.patienthub.Exceptions;

import java.util.NoSuchElementException;

public class ResourceNotFoundException extends NoSuchElementException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
