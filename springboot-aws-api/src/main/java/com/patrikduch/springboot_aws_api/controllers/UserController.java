package com.patrikduch.springboot_aws_api.controllers;

import com.patrikduch.springboot_aws_api.daos.UserDaoImpl;
import com.patrikduch.springboot_aws_api.service.ExtendedUserService;
import com.patrikduch.springboot_aws_api.utils.JwtUtil;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.User;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.auth.AuthenticationRequest;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.auth.AuthenticationResponse;
import com.patrikduch.springboot_aws_api_core.dtos.user_management.user.crud.CreateUserRequest;
import com.patrikduch.springboot_aws_api_core.exceptions.BadRequestBodyException;
import com.patrikduch.springboot_aws_api_core.exceptions.UnauthorizedResponseException;
import com.patrikduch.springboot_aws_api_core.interfaces.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST API for managing all user neccessary actions (user creation, authentification, authorization, etc.)
 * @author Patrik Duch
 */
@RestController
@Tag(name = "UserAPI", description = "Management of user (user creation, authentification, authorization, etc.)")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private AuthenticationManager _authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Qualifier("UserDetailsService")
    @Autowired
    private ExtendedUserService _userDetailService;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private UserDaoImpl _userDao;

    @Autowired
    private MongoOperations _mongoOperatiosn;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UserModel has been successfully registered.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})})
    @Operation(summary = "Registration of a new user", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<User> createUser (@RequestBody CreateUserRequest user) throws Exception {

        var entityUser =_userDao.createUser(user);

        if(entityUser == null) {
            throw new Exception("Cannot add a new user with this role.");
        }

        var modelMapper = new ModelMapper();
        var responseEntity = modelMapper.map(entityUser, User.class);

        return new ResponseEntity<>(responseEntity, HttpStatus.CREATED);
    }


    @Operation(summary = "Fetch list of users", description = "Get a list of registered user", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched list of user.",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))})
    @GetMapping(value = "", produces = { "application/json" })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Parameter(in = ParameterIn.HEADER, description = "Token is needed to process this request successfully.", name = "Authorization", schema = @Schema(type = "string"))
    public ResponseEntity<Iterable<User>> getUsers() throws BadRequestBodyException {

        var entityList = _userRepository.findAll();

        if (entityList.isEmpty()) {
            throw new BadRequestBodyException("User list is empty");
        }

        ModelMapper modelMapper = new ModelMapper();
        var dtoList = new ArrayList<User>();

        entityList.forEach((user -> {
            dtoList.add(modelMapper.map(user, User.class));
        }));

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully fetched user by it's id.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})})
    @Operation(summary = "Fetch a user by id", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping(value = "/{id}", produces = { "application/json" })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Parameter(in = ParameterIn.HEADER, description = "Token is needed to process this request successfully.", name = "Authorization", schema = @Schema(type = "string"))
    public ResponseEntity<User> getUser(@PathVariable Integer id) throws BadRequestBodyException {

        // 1. Find user entity by it's id.
        var entity = _userRepository.findById(id);

        if (entity.isEmpty()) {
            throw new BadRequestBodyException("User with id:" +  id + " cannot be found.");
        }

        // 2. Create a response from fetched User entity
        var responseEntity = new User();

        // 3. Mapping source object into DTO
        ModelMapper modelMapper = new ModelMapper();
        responseEntity = modelMapper.map(entity.get(), User.class);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been successfully authenticated.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})})
    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @Operation(summary = "Get details about authenticated user", security = @SecurityRequirement(name = "bearerAuth"))
    @Parameter(in = ParameterIn.HEADER, description = "Token is needed to process this request successfully.", name = "Authorization", schema = @Schema(type = "string"))
    public ResponseEntity<User> getCurrentUser(@RequestHeader( required = false, value = "Authorization") String authToken) throws Exception {

        User entity = null;

        if (authToken == null) throw new UnauthorizedResponseException("Invalid token");

        try {
            entity =  _userDao.getUserInfo(authToken);
        } catch (Exception ex) {
            throw new UnauthorizedResponseException("Invalid token");
        }

        return new ResponseEntity<>(entity, HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sign in an unauthenticated user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))}),
    })
    @Operation(summary = "User login")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws  Exception {

        var entity = _userDao.findByEmail(authenticationRequest.getEmail());

        var authToken =  new UsernamePasswordAuthenticationToken(
                entity.getUsername(),
                authenticationRequest.getPassword());

        try {
            _authenticationManager.authenticate(authToken);

        } catch (Exception ex) {

            throw new Exception("Incorrect username or password!", ex);
        }

        final UserDetails userDetails = _userDetailService.loadUserByUsername(entity.getUsername());
        final String jwt =  jwtUtil.generateToken(userDetails);

        return  ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Block a registered user account", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
    })
    @Operation(summary = "Block a registered user account")
    @PostMapping("/block/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> disableUser(@PathVariable int userId)
            throws BadRequestBodyException {

        // 1. Find user entity by it's id.
        var entity = _userDao.findById(userId);

        if (entity == null) {
            throw new BadRequestBodyException("User with id:" +  userId + " cannot be found.");
        }

        // 2. Update user isDisabled status
        entity.setDisabled(true);
        _userRepository.save(entity);

        // 3. Return the response of updated user
        var modelMapper  =  new ModelMapper();
        return  ResponseEntity.ok(modelMapper.map(entity, User.class));
    }

}