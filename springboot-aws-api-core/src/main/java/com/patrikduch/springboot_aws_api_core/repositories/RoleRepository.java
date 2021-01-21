package com.patrikduch.springboot_aws_api_core.repositories;

import com.patrikduch.springboot_aws_api_core.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Integer> { }
