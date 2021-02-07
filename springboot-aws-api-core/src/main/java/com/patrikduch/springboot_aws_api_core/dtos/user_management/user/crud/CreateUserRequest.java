package com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud;

import lombok.Getter;
import lombok.Setter;

/**
 * Simplified user object that is needed for new user creation.
 * @author Patrik Duch
 */
@Getter
@Setter
public class CreateUserRequest {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String[] roles;
}
