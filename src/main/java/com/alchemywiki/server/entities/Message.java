package com.alchemywiki.server.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder @ToString @Getter
public class Message {
  @Id
  private String id;

  private String content;

  @DBRef(lazy = true)
  private User user;

}
