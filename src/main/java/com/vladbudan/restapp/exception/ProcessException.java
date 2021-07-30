package com.vladbudan.restapp.exception;


import java.io.IOException;

public class ProcessException extends IOException {

    public ProcessException(String message) {
        super(message);
    }

}
