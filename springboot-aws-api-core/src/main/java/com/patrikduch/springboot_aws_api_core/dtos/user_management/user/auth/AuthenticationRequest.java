package com.patrikduch.springboot_aws_api_core.dtos.user_management.user.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request body representation for handling authentication process.
 * @author Patrik Duch
 */
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.email = username;
        this.password = password;
    }
}