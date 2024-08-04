package com.crio.starter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "memes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meme {

  @Transient
    public static final String SEQUENCE_NAME = "memes_sequence";

  @Id
  private long id;

  private String name;

  private String url;

  private String caption;

  private LocalDate dateOfUpload;
  
}