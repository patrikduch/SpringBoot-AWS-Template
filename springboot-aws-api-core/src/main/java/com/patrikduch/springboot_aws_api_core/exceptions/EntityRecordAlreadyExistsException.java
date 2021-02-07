package com.patrikduch.springboot_aws_api_core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for handling duplicity of MongoDb entities.
 * @author Patrik Duch
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EntityRecordAlreadyExistsException extends Exception {
    public EntityRecordAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
