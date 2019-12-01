package com.alchemywiki.server.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Builder @ToString @Getter
public class Log {
  @Id
  private String id;

  private String description;

  @DBRef(lazy = true)
  private User user;
}
