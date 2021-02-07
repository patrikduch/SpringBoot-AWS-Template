package com.patrikduch.springboot_aws_api.service;

import com.patrikduch.springboot_aws_api.utils.SequenceGeneratorService;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud.CreateUserRequest;
import com.patrikduch.springboot_aws_api_core.exceptions.BadRequestBodyException;
import com.patrikduch.springboot_aws_api_core.exceptions.EntityRecordAlreadyExistsException;
import com.patrikduch.springboot_aws_api_core.interfaces.repositories.UserRepository;
import com.patrikduch.springboot_aws_api_core.interfaces.services.RoleService;
import com.patrikduch.springboot_aws_api_core.interfaces.services.UserService;
import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Arbitrary service that encapsulates logic for user's management.
 * @author Patrik Duch
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository _userRep;

    @Autowired
    private PasswordEncoder _passwordEncoder;

    @Autowired
    private RoleService _roleService;

    @Autowired
    private SequenceGeneratorService _sequenceGeneratorService;

    /**
     * User model vefification process.
     * @param user Creation request dto for creating a brand new user account.
     */
    public void verifyModel(CreateUserRequest user) throws EntityRecordAlreadyExistsException, BadRequestBodyException {

        if(user.getUsername() == null || user.getPassword() == null ||
                user.getFirstName() == null ||  user.getLastName() == null
                || user.getEmail() == null)
        {
            throw new BadRequestBodyException("Invalid user credentials");
        }

        try {
            if (user.getPassword() == null) throw new BadRequestBodyException("Password cannot be blank!");
            var entity = _userRep.findByEmail(user.getEmail());

            if (entity != null)  throw new BadRequestBodyException("This user already exists!");

            return; // Everything is ok

        } catch (IncorrectResultSizeDataAccessException ex) {

            throw new EntityRecordAlreadyExistsException("This user already exists");
        }
    }

    /**
     * User model sanitizatio process (filling neccessary fields like (generated unique identifier etc.)
     * @param user Creation request dto for creating a brand new user account.
     */
    @Override
    public void sanityModel(CreateUserRequest user) {
        // Add unique identifier
        user.setId(_sequenceGeneratorService.generateSequence(UserModel.SEQUENCE_NAME));

        // Encrypt the entered password
        user.setPassword(_passwordEncoder.encode(user.getPassword()));
    }

    /**
     * Assign roles to the particular UserModel entity.
     * @param user Creation request dto for creating a brand new user account.
     * @param userModel UserModel represents final entity that represents information
     *                 that will be stored in the database..
     */
    @Override
    public void assignRoleToUser(CreateUserRequest user, UserModel userModel) {
        var rolesInput = user.getRoles();
        var rolesOuputEnum = _roleService.getValues(rolesInput);
        // Assign roles to the user
        userModel.setRoles(rolesOuputEnum);
    }

}