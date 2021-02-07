package com.patrikduch.springboot_aws_api_core.dtos.user_management.user.auth;

import lombok.Getter;

/**
 * Response of an authenticated user, which consists of basic authentification tokens.
 * @author Patrik Duch
 */
@Getter
public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {

        this.jwt = jwt;
    }
}