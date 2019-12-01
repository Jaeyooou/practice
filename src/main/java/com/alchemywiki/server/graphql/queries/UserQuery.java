package com.alchemywiki.server.graphql.queries;

import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {

  private final UserRepository repository;

  public User getUser(String id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
  }

  public List<User> getUsers() {
    return repository.findAll();
  }
}
