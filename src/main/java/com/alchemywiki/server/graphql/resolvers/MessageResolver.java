package com.alchemywiki.server.graphql.resolvers;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.Message;
import com.alchemywiki.server.entities.User;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageResolver implements GraphQLResolver<Message> {

  public User getUser(Message message){
    return message.getUser();
  }
}
