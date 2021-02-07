package com.patrikduch.springboot_aws_api_core.dtos.project_details;

import lombok.Getter;
import lombok.Setter;

/**
 * Data transfer object that represents request body for project detail management (update or creation).
 * @author Patrik Duch
 */
@Getter
@Setter
public class ProjectDetailRequest {
    private String name;
}
