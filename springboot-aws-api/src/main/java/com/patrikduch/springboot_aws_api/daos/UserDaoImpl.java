package com.patrikduch.springboot_aws_api.daos;

import com.patrikduch.springboot_aws_api.utils.JwtUtil;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.User;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud.CreateUserRequest;
import com.patrikduch.springboot_aws_api_core.exceptions.BadRequestBodyException;
import com.patrikduch.springboot_aws_api_core.exceptions.EntityRecordAlreadyExistsException;
import com.patrikduch.springboot_aws_api_core.exceptions.UnauthorizedResponseException;
import com.patrikduch.springboot_aws_api_core.interfaces.daos.UserDao;
import com.patrikduch.springboot_aws_api_core.interfaces.repositories.UserRepository;
import com.patrikduch.springboot_aws_api_core.interfaces.services.UserService;
import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A MongoDb Document implementation of {@link UserDao}.
 * @author Patrik Duch
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private @NotNull JwtUtil _jwtUtil;

    @Autowired
    private  @NotNull UserRepository _userRep;

    @Autowired
    private @NotNull UserService _userService;

    @Override
    public UserModel findById(final @NotNull Integer id) {
        return _userRep.findById(id).orElse(null);
    }

    /**
     * Find user by provided e-mail address.
     * @param email E-mail address that is used for user search.
     * @return UserModel User entity tha contains all mapped properties from MongoDb.
     */
    @Override
    public UserModel findByEmail(final @NotEmpty String email) throws BadRequestBodyException {

        var entity = _userRep.findByEmail(email);
        if (entity == null) throw new BadRequestBodyException("Incorrect user credentials.");

        return entity;
    }

    /**
     * Find user by provided username.
     * @param username Username that is used for user search.
     * @return UserModel User entity tha contains all mapped properties from MongoDb.
     */
    @Override
    public UserModel findByUsername(@NotEmpty String username) throws BadRequestBodyException {

        var entity = _userRep.findByEmail(username);
        if (entity == null) throw new BadRequestBodyException("Incorrect user credentials.");

        return entity;
    }

    @Override
    public List<UserModel> findAllByEmail(@NotEmpty String partialEmail) {
        return _userRep.findAllByEmailContaining(partialEmail);
    }

    /**
     * Create a new user
     * @param user User object that is used to create a new user.
     * @return UserModel MongoDb User entity with all mapped fields.
     */
    @Override
    public UserModel createUser(final @NotNull CreateUserRequest user) throws BadRequestBodyException, EntityRecordAlreadyExistsException, IncorrectResultSizeDataAccessException, UnauthorizedResponseException {

        // 1. Check the entered credentials
        _userService.verifyModel(user);

        // 2. Sanitization of User model
        _userService.sanityModel(user);

        // 3. Mapping to the  response object
        ModelMapper modelMapper = new ModelMapper();
        var userModel = modelMapper.map(user, UserModel.class);

        // 4. Role assignment
        _userService.assignRoleToUser(user, userModel);

        // Create a new record
        _userRep.save(userModel);

        return userModel;
    }

    /**
     * Get information about authenticated user.
     * @param authToken JWT token that will be parsed to get information about authenticated user.
     * @return User User DTO tha contains all needed mapped properties from MongoDb.
     */
    @Override
    public User getUserInfo(String authToken) {

        // 1. Parse username from JWT token
        var username = _jwtUtil.extractUsername(authToken);

        // 2. Get credential from database
        var userEntity = _userRep.findByUsername(username);

        // 3. Prepare response of user entity
        var modelMapper = new ModelMapper();
        return modelMapper.map(userEntity, User.class);
    }

}