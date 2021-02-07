package com.patrikduch.springboot_aws_api_core.interfaces.daos;

import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.User;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud.CreateUserRequest;
import com.patrikduch.springboot_aws_api_core.exceptions.BadRequestBodyException;
import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * An interface for managing {@link User}'s model.
 * @author Patrik Duch
 */
public interface UserDao {

    /**
     * Gets a {@link UserModel} for a specific {@link UserModel#getId()}.
     *
     * @param id
     *            the {@link UserModel#getId()} of the {@link UserModel} to find.
     * @return a {@link UserModel} for the given id. Cannot be null.
     * @throws EmptyResultDataAccessException
     *             if the {@link UserModel} cannot be found
     */
    UserModel findById(@NotNull Integer id);

    /**
     * Finds a given {@link UserModel} by email address.
     *
     * @param email
     *            the email address to use to find a {@link UserModel}. Cannot be null.
     * @return a {@link UserModel} for the given email or null if one could not be found.
     * @throws IllegalArgumentException
     *             if email is null.
     */
    UserModel findByEmail(@NotEmpty String email) throws BadRequestBodyException;

    /**
     * Finds a given {@link UserModel} by username.
     *
     * @param username
     *            the username to use to find a {@link UserModel}. Cannot be null.
     * @return a {@link UserModel} for the given username or null if one could not be found.
     * @throws IllegalArgumentException
     *             if username is null.
     */
    UserModel findByUsername(@NotEmpty String username) throws BadRequestBodyException;

    /**
     * Finds any {@link UserModel} that has an email that starts with {@code partialEmail}.
     *
     * @param partialEmail
     *            the email address to use to find {@link UserModel}s. Cannot be null or empty String.
     * @return a List of {@link UserModel}s that have an email that starts with given partialEmail. The returned value
     *         will never be null. If no results are found an empty List will be returned.
     * @throws IllegalArgumentException
     *             if email is null or empty String.
     */
    List<UserModel> findAllByEmail(@NotEmpty String partialEmail);

    /**
     * Creates a new user record.
     * @param user
     *            {@link CreateUserRequest} contains information about a new user.
     * @return the new {@link UserModel}.
     */
    UserModel createUser(@NotNull CreateUserRequest user) throws Exception;

    /**
     * Get basic information about authenticated user.
     * @param authToken String representation of encoded user information (username).
     * @return {@link User} object instance of authenticated user.
     */
    User getUserInfo(String authToken);

}