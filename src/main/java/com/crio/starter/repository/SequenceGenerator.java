package com.crio.starter.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.Objects;
import com.crio.starter.entity.DatabaseSeq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class SequenceGenerator {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public long generateSequence(String seqName) {

        Query query = new Query(Criteria.where("id").is(seqName));
        DatabaseSeq counter = mongoTemplate.findAndModify(query, new Update().inc("sequenceNo", 1), FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSeq.class, "database_sequences");
        return !Objects.isNull(counter) ? counter.getSequenceNo() : 1;
    }
}