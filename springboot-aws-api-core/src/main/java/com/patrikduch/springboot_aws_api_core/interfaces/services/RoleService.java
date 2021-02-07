package com.patrikduch.springboot_aws_api_core.interfaces.services;

import com.patrikduch.springboot_aws_api_core.enums.RoleEnum;
import java.util.Set;

/**
 * An interface for arbitrary service, that handles additional logic for role management.
 * @author Patrik Duch
 */
public interface RoleService {
    Set<RoleEnum> getValues(String[] roleNames);
}
