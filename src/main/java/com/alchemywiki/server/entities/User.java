package com.alchemywiki.server.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Builder @ToString @Getter
public class User {
  @Id
  private String id;

  @NotNull
  @Length(min = 4, max = 20)
  @Indexed(unique = true)
  private String name;
}
