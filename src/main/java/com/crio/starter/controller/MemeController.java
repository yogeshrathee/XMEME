package com.crio.starter.controller;

import com.crio.starter.entity.Meme;
import com.crio.starter.exceptions.InvalidMemeException;
import com.crio.starter.exceptions.MemeNotFoundException;
import com.crio.starter.exchange.SaveMemeResponse;
import com.crio.starter.repository.MemeRepositoryImpl;
import com.crio.starter.service.MemeService;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeController {

  
  private final MemeService memeService;
  private final MemeRepositoryImpl memeRepository;
  

  public MemeController(MemeService memeService, MemeRepositoryImpl memeRepository) {
    this.memeService = memeService;
    this.memeRepository= memeRepository;
  }

  @GetMapping("/memes")
  public ResponseEntity<List<Meme>> getMemes(){
    List<Meme> memes = memeService.getMemes();
    return new ResponseEntity<>(memes, HttpStatus.OK);
  }

  @GetMapping("/memes/{id}")
  public ResponseEntity<?> getMeme(@PathVariable(name="id") long id) throws MemeNotFoundException {
    Meme meme = memeService.getMeme(id);
    if(meme == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // System.out.print(meme.getId().toString());
    return new ResponseEntity<>(meme, HttpStatus.OK);
  }

  @PostMapping("/memes")
  public ResponseEntity<?> saveMeme(@RequestBody Meme meme) throws InvalidMemeException{
    // System.out.println(meme);
    if(meme.getName() == null || meme.getUrl()==null || meme.getCaption()==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    if(memeRepository.checkDuplicates(meme)){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    long id = memeService.saveMeme(meme);
    SaveMemeResponse response = new SaveMemeResponse(String.valueOf(id));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PatchMapping("/memes/{id}")
  public ResponseEntity<String> updateMeme(@RequestBody Map<String, Object> updates, @PathVariable(name="id") long id) throws MemeNotFoundException, InvalidMemeException{
    memeService.updateMeme(updates, id);
    return ResponseEntity.ok("Meme Updated");
  }

}