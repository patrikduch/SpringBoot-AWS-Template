package com.patrikduch.springboot_aws_api.controllers;

import com.patrikduch.springboot_aws_api.utils.SequenceGeneratorService;
import com.patrikduch.springboot_aws_api_core.dtos.project_details.ProjectDetail;
import com.patrikduch.springboot_aws_api_core.dtos.project_details.ProjectDetailRequest;
import com.patrikduch.springboot_aws_api_core.interfaces.repositories.ProjectDetailRepository;
import com.patrikduch.springboot_aws_api_core.models.project_details.ProjectDetailModel;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * REST API for managing basic project details.
 * @author Patrik Duch
 */
@RestController
@Tag(name = "Project detail API", description = "Management of basic project details.")
@RequestMapping("/api")
public class ProjectDetailController {

    @Autowired
    ProjectDetailRepository _projectDetailRepository;

    @Autowired
    SequenceGeneratorService _sequenceGeneratorService;

    @ApiResponse(responseCode = "200", description = "Successfully fetched project detail.",
            content = @Content(schema = @Schema(implementation = ProjectDetail.class)))
    @Operation(summary = "Fetch project details")
    @RequestMapping(value = "/project", method = RequestMethod.GET)
    public ResponseEntity<ProjectDetail> getProjectDetail() throws NoSuchElementException {

        var entity = _projectDetailRepository.findFirstByOrderByIdDesc();

        if (entity == null) {
            throw new NoSuchElementException("Cannot find detail about project");
        }

        var modelMapper = new ModelMapper();
        var responseEntity = modelMapper.map(entity, ProjectDetail.class);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @ApiResponse(responseCode = "200", description = "Successfully updated project detail.",
            content = @Content(schema = @Schema(implementation = ProjectDetail.class)))
    @Operation(summary = "Create or update project details", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(value = "/project", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Parameter(in = ParameterIn.HEADER, description = "Token is needed to process this request successfully.", name = "Authorization", schema = @Schema(type = "string"))
    public ResponseEntity<ProjectDetail> createorUpdateProjectDetail(@Valid @RequestBody ProjectDetailRequest projectDetailRequest) throws NoSuchElementException {

        ProjectDetailModel entity = null;
        try {
            entity = _projectDetailRepository.findById(1).get();

        } catch (NoSuchElementException ex) {
            entity = new ProjectDetailModel();
        }

        var projectId = _sequenceGeneratorService.generateSequence(ProjectDetailModel.SEQUENCE_NAME);
        entity.setId(projectId);

        entity.setName(projectDetailRequest.getName());
        _projectDetailRepository.save(entity);

        var modelMapper = new ModelMapper();
        var responseEntity = modelMapper.map(entity, ProjectDetail.class);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}