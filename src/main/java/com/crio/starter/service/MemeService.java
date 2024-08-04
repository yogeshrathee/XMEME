package com.crio.starter.service;

import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.InvalidMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Map;

public interface MemeService {

    List<Meme> getMemes();
    Meme getMeme(long id) throws MemeNotFoundException;
    long saveMeme(Meme meme) throws InvalidMemeException;
    void updateMeme(Map<String, Object> updates, long id) throws InvalidMemeException, MemeNotFoundException;
}