package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MemeNotFoundException extends Exception{
    public MemeNotFoundException(){
        super("Meme not found");
    }
}