package com.patrikduch.springboot_aws_api.service;
import com.patrikduch.springboot_aws_api_core.model.ProjectDetailDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectDetailService {

    public ProjectDetailDto getProjectDetail(){
        return new ProjectDetailDto(1, "Project name");
    }
}
