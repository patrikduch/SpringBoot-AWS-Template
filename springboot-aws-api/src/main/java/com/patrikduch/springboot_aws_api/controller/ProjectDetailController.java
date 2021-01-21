package com.patrikduch.springboot_aws_api.controller;

import com.patrikduch.springboot_aws_api_core.dtos.ProjectDetailDto;
import com.patrikduch.springboot_aws_api.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectDetailController {

    @Autowired
    ProjectDetailService planeService;

    @GetMapping(value = "/project")
    public ResponseEntity<ProjectDetailDto> getPlane(){
        var projectDetailDto = planeService.getProjectDetail();
        return new ResponseEntity<>(projectDetailDto, HttpStatus.OK);
    }
}
