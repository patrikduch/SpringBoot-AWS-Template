package com.patrikduch.springboot_aws_api.service;
import com.patrikduch.springboot_aws_api_core.dtos.ProjectDetailDto;
import org.springframework.stereotype.Service;

@Service
public class ProjectDetailService {

    public ProjectDetailDto getProjectDetail(){

        var entity = new ProjectDetailDto();

        entity.setId(1);
        entity.setName("Project name");

        return entity;
    }
}
