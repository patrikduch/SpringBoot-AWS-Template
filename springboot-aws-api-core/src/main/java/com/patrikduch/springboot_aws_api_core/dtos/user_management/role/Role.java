package com.patrikduch.springboot_aws_api_core.dtos.user_management.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Role {
    private String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}