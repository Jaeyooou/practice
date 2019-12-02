package com.alchemywiki.server.graphql.resolvers;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.repositories.LogRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLResolver<User> {
  private final LogRepository repository;

  public List<Log> getLogs(User user, DataFetchingEnvironment env) {
    return repository.findByUser(user);
  }
}
