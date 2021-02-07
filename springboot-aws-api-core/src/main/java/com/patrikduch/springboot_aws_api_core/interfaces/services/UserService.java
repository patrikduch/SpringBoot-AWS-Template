package com.patrikduch.springboot_aws_api_core.interfaces.services;

import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud.CreateUserRequest;
import com.patrikduch.springboot_aws_api_core.exceptions.BadRequestBodyException;
import com.patrikduch.springboot_aws_api_core.exceptions.EntityRecordAlreadyExistsException;
import com.patrikduch.springboot_aws_api_core.exceptions.UnauthorizedResponseException;
import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;

/**
 * An interface for arbitrary service, that handles additional logic for user management.
 * @author Patrik Duch
 */
public interface UserService {
    void verifyModel(CreateUserRequest user) throws UnauthorizedResponseException, EntityRecordAlreadyExistsException, BadRequestBodyException;
    void sanityModel(CreateUserRequest user);
    void assignRoleToUser(CreateUserRequest user, UserModel userModel);
}
