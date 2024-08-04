package com.crio.starter.repository;

import java.util.List;
import java.util.Map;
import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.InvalidMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import org.bson.types.ObjectId;

public interface MemeRepository {

    List<Meme> getMemes();
    
    Meme getMeme(long id) throws MemeNotFoundException;

    long saveMeme(Meme meme) throws InvalidMemeException;

    void updateMeme(Map<String, Object> updates, long id) throws MemeNotFoundException;
}