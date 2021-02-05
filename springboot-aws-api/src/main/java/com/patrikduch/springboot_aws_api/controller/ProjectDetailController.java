package com.patrikduch.springboot_aws_api.controller;

import com.patrikduch.springboot_aws_api.ProjectDetailApi;
import com.patrikduch.springboot_aws_api_core.model.ProjectDetailDto;
import com.patrikduch.springboot_aws_api.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projectDetail")
public class ProjectDetailController implements  ProjectDetailApi{

    @Autowired
    ProjectDetailService _projectDetailService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @Override
    public ResponseEntity<ProjectDetailDto> getProjectDetail() {
        return new ResponseEntity<>(_projectDetailService.getProjectDetail(), HttpStatus.OK);
    }
}
