package com.patrikduch.springboot_aws_api.service;

import com.patrikduch.springboot_aws_api.spring_security.ExtendedUser;
import com.patrikduch.springboot_aws_api.utils.JwtUtil;
import com.patrikduch.springboot_aws_api_core.interfaces.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security overriden user management service.
 * @author Patrik Duch
 */
@Service("UserDetailsService")
public class ExtendedUserService implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private JwtUtil _jwtUtil;

    /**
     * Find user by it's username.
     * @param username Username
     * @return UserDetails that represents Spring Security User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userEntity = _userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }

        return new ExtendedUser(userEntity);
    }
}
