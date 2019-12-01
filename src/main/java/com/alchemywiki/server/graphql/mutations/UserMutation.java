package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.repositories.UserRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMutation implements GraphQLMutationResolver {

  private final UserRepository userRepository;

  public User addUser(String name) {
    return userRepository.save(User.builder().name(name).build());
  }
}
