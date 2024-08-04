package com.crio.starter.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.InvalidMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MemeRepositoryImpl implements MemeRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Meme> getMemes() {
        Query query = new Query().with(Sort.by(Direction.DESC, "id")).limit(100);
        List<Meme> memes = mongoTemplate.find(query, Meme.class);
        if (memes == null || memes.isEmpty()) {
            return Collections.emptyList();
        }
        return memes;
    }

    @Override
    public Meme getMeme(long id) throws MemeNotFoundException {
        // long nid = mongoTemplate.fi
        Query query = new Query(Criteria.where("id").is(id));
        Meme meme = mongoTemplate.findOne(query, Meme.class, "memes");
        if (meme == null) {
            throw new MemeNotFoundException();
        }
        return meme;
    }

    @Override
    public long saveMeme(Meme meme) throws InvalidMemeException {
        if(checkDuplicates(meme)){
            throw new InvalidMemeException();
        }
        // meme.setId(new ObjectId());
        Meme newMeme = mongoTemplate.save(meme, "memes");
        return newMeme.getId();
    }

    @Override
    public void updateMeme(Map<String, Object> updates, long id) throws MemeNotFoundException {
        Query query = new Query(Criteria.where("id").is(id));
        Meme meme = mongoTemplate.findOne(query, Meme.class, "memes");
        if (meme == null) {
            throw new MemeNotFoundException();
        }
        Object updatedUrl = updates.get("url");
        Object updatedCaption = updates.get("caption");
        if (updatedUrl != null) {
            meme.setUrl(updatedUrl.toString());
        }
        if (updatedCaption != null) {
            meme.setCaption(updatedCaption.toString());
        }
        mongoTemplate.save(meme);
    }

    public boolean checkDuplicates(Meme meme) {
        List<Meme> memeEntities = getMemes();
        for(Meme memeEntity: memeEntities) {
            if(meme.getName().equals(memeEntity.getName()) && meme.getUrl().equals(memeEntity.getUrl()) &&
            meme.getCaption().equals(memeEntity.getCaption())) {
                return true;
            }
        }

        return false;
    }
}