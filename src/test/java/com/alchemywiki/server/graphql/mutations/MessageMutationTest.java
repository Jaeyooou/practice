package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.Message;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.LogRepository;
import com.alchemywiki.server.repositories.MessageRepository;
import com.alchemywiki.server.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(SpringRunner.class)
@DataMongoTest
public class MessageMutationTest {

  @Autowired private MessageRepository messageRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private MongoTemplate mongoTemplate;

  private MessageMutation mutation;

  private User user1 = User.builder().name("user1").build();

  @Before
  public void before(){
    mongoTemplate.save(user1);
    mutation = new MessageMutation(userRepository, messageRepository);
  }
}
