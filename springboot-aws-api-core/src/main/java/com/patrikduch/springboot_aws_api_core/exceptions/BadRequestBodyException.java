package com.patrikduch.springboot_aws_api_core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom response exception for handling emptiness or incompleteness of request body.
 * @author Patrik Duch
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestBodyException extends Exception {
    public BadRequestBodyException(String errorMessage) {
        super(errorMessage);
    }
}
