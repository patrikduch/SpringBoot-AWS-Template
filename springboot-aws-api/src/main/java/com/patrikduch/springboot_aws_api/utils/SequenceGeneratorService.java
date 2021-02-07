package com.patrikduch.springboot_aws_api.utils;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import com.patrikduch.springboot_aws_api_core.models.mongodb.DatabaseSequenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


/**
 * Custom  unique sequence generator for MongoDb entities.
 * @author Patrik Duch
 */
@Service
public class SequenceGeneratorService {

    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * Generate sequence for MongoDb entity.
     * @return Long id identifier of future document entity..
     */
    public long generateSequence(String seqName) {

        DatabaseSequenceModel counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequenceModel.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }
}