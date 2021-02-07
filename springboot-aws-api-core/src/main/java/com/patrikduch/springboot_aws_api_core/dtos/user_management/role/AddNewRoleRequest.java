package com.patrikduch.springboot_aws_api_core.dtos.user_management.role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewRoleRequest
{
    private String roleName;
}