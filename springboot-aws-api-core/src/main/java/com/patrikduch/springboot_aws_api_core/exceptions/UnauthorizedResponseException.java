package com.patrikduch.springboot_aws_api_core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling unauthorized access of HTTP requests.
 * @author Patrik Duch
 */
@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedResponseException  extends  Exception {
    public UnauthorizedResponseException(String message) {
        super(message);
    }
}