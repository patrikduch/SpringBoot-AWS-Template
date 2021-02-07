package com.patrikduch.springboot_aws_api.service;

import com.patrikduch.springboot_aws_api_core.enums.RoleEnum;
import com.patrikduch.springboot_aws_api_core.interfaces.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Arbitrary service that encapsulates logic for role's management.
 * @author Patrik Duch
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * Get information about authenticated user.
     * @param roleNames Array of listed roles for the current user.
     * @return Set RoleEnum set that contains information about roles that will be assigned to specific user.
     */
    public Set<RoleEnum> getValues(String[] roleNames) {

        Set<RoleEnum> roleSet = new HashSet<>();

        for (var roleName: roleNames) {
            switch(roleName) {

                case "Admin":
                    roleSet.add(RoleEnum.ADMIN);
                    break;

                case "Submitter":
                    roleSet.add(RoleEnum.SUBMITTER);
                    break;

                case "Reviewer":
                    roleSet.add(RoleEnum.REVIEWER);
                    break;
            }
        }

        return roleSet;
    }

}