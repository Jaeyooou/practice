package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.LogRepository;
import com.alchemywiki.server.repositories.UserRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogMutation implements GraphQLMutationResolver {

  private final LogRepository logRepository;
  private final UserRepository userRepository;

  public Log addLog(String description, String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
    return logRepository.save(Log.builder().description(description).user(user).build());
  }
}
