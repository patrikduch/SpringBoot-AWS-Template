package com.patrikduch.springboot_aws_api_core.dtos.user_management.user;

import com.patrikduch.springboot_aws_api_core.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Simplified user object that is transfered as the response for all endpoints, that doesn't need
 * all properties from Use MongoDb entity.
 * @author Patrik Duch
 */
@Getter
@Setter
public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<RoleEnum> roles = new HashSet<>();
}
