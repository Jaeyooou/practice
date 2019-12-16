package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.entities.Message;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.LogRepository;
import com.alchemywiki.server.repositories.MessageRepository;
import com.alchemywiki.server.repositories.UserRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class MessageMutation implements GraphQLMutationResolver{

  private final UserRepository userRepository;
  private final MessageRepository messageRepository;
  private final LogRepository logRepository;
  public Message addMessage(String content, String userId) {

    User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
    return messageRepository.save(Message.builder().content(content).user(user).build());
  }
}
