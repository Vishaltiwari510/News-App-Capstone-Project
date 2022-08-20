package com.stackroute.capstone.apigateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtTokenIncorrectStructureException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public JwtTokenIncorrectStructureException(String message) {
        super(message);
    }
}