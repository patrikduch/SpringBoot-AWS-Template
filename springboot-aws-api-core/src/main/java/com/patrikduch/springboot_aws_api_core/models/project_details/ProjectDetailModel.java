package com.patrikduch.springboot_aws_api_core.models.project_details;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model that represents information about project.
 * @author Patrik Duch
 */
@Document(value = "ProjectDetail")
@Getter
@Setter
@NoArgsConstructor
public class ProjectDetailModel {

    private Long id;
    private String name;

    @Transient
    public static final String SEQUENCE_NAME = "project_detail_sequence";

    @PersistenceConstructor
    public ProjectDetailModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
