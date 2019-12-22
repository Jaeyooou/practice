package com.alchemywiki.server.graphql.resolvers;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.entities.Message;
import com.alchemywiki.server.repositories.LogRepository;
import com.alchemywiki.server.repositories.MessageRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLResolver<User> {
  private final LogRepository repository;
  private final MessageRepository repository2;

  public List<Log> getLogs(User user, DataFetchingEnvironment env) {
    return repository.findByUser(user);
  }

  public List<Message> getMassages(User user, DataFetchingEnvironment env) {
    return repository2.findByUser(user);
  }

}
