package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(String id) {
    GreetingsEntity entity = greetingsRepository.findByExtId(id);
    if (entity != null) {
      return new ResponseDto(entity.getMessage());
    } else {
      return new ResponseDto("Message not found");
    }
  }
}
