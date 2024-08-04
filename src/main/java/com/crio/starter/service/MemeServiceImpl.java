package com.crio.starter.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.InvalidMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.repository.SequenceGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeServiceImpl implements MemeService{

    @Autowired
    private MemeRepository memeRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Override
    public List<Meme> getMemes() {
        List<Meme> memes= memeRepository.getMemes();
        // List<MemeDto> memeDtos= new ArrayList<>();
        // for(Meme meme: memes){
        //     memeDtos.add(mapper.map(meme, MemeDto.class));
        // }
        return memes;
    }

    @Override
    public Meme getMeme(long id) throws MemeNotFoundException {
        Meme meme= memeRepository.getMeme(id);
        if (meme == null) {
            throw new MemeNotFoundException();
        }
        return meme;
    }

    @Override
    public long saveMeme(Meme meme) throws InvalidMemeException {
        // Meme meme1 = new Meme(Long.valueOf(memeDto.getId()), memeDto.getName(),
        //             memeDto.getUrl(),memeDto.getCaption(), LocalDate.now());
        meme.setId(sequenceGenerator.generateSequence(Meme.SEQUENCE_NAME));
        meme.setDateOfUpload(LocalDate.now());
        long response = memeRepository.saveMeme(meme);
        return response;
    }

    @Override
    public void updateMeme(Map<String, Object> updates, long id) throws InvalidMemeException, MemeNotFoundException{
        Object newUrl = updates.get("url");

        Object newCaption = updates.get("caption");

        if (newUrl != null) {
            throw new InvalidMemeException("No url provided.");
        }

        if (newCaption != null) {
            throw new InvalidMemeException("Caption is blank.");
        }

        memeRepository.updateMeme(updates, id);
    }
    
}