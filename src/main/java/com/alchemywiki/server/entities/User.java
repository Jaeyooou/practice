package com.alchemywiki.server.entities;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Builder
public class User {
  @Id
  private String id;

  @NotNull
  @Length(min = 4, max = 20)
  private String name;
}
