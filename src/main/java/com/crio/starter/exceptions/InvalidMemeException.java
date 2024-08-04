package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidMemeException extends Exception {
    public InvalidMemeException(){
        super("Invalid meme");
    }

    public InvalidMemeException(String messageString) {
        super(messageString);
    }
}