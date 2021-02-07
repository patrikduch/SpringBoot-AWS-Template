package com.patrikduch.springboot_aws_api_core.interfaces.repositories;

import com.patrikduch.springboot_aws_api_core.models.project_details.ProjectDetailModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data interface for accessing projectdetail informations.
 * @author Patrik Duch
 */
public interface ProjectDetailRepository extends MongoRepository<ProjectDetailModel, Integer> {
    ProjectDetailModel findFirstByOrderByIdDesc();
}
