package com.patrikduch.springboot_aws_api_core.models.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model that represents sequences for every MongoDb entity.
 * @author Patrik Duch
 */
@Document(collection = "DatabaseSequences")
@Data
public class DatabaseSequenceModel {
    @Id
    private String id;
    private long seq;
}