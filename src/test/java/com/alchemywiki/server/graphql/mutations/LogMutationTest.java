package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.LogRepository;
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
public class LogMutationTest {

  @Autowired private LogRepository logRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private MongoTemplate mongoTemplate;

  private LogMutation mutation;

  private User user1 = User.builder().name("user1").build();

  @Before
  public void before() {
    mongoTemplate.save(user1);
    mutation = new LogMutation(logRepository, userRepository);
    System.out.println(mongoTemplate.findAll(User.class));
  }

  @After
  public void after() {
    mongoTemplate.getDb().drop();
  }

  @Test
  public void addLogTest() {
    String description = "test";
    Log log = mutation.addLog(description, user1.getId());

    Log found = mongoTemplate.findById(log.getId(), Log.class);

    assertThat(found).isNotNull();
    assertThat(found.getDescription()).isEqualTo(description);
    assertThat(found.getUser().getId()).isEqualTo(user1.getId());
  }

  @Test
  public void addLogWithInvalidUserIdTest() {
    String description = "test";
    assertThatThrownBy(() -> mutation.addLog(description, "INVALID USER ID"))
        .isInstanceOf(NotFoundException.class);
  }
}
