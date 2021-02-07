package com.patrikduch.springboot_aws_api_core.interfaces.repositories;

import com.patrikduch.springboot_aws_api_core.models.user_management.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * Spring Data interface for accessing and manipulation with list of current users.
 * @author Patrik Duch
 */
public interface UserRepository extends MongoRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
    List<UserModel> findAllByEmailContaining(String partialEmail);
}