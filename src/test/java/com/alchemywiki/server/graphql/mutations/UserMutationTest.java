package com.alchemywiki.server.graphql.mutations;

import com.alchemywiki.server.entities.User;
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


@RunWith(SpringRunner.class)
@DataMongoTest
public class UserMutationTest {

  @Autowired private UserRepository repository;
  @Autowired private MongoTemplate mongoTemplate;

  private UserMutation mutation;

  @Before
  public void before() {
    mutation = new UserMutation(repository);
  }

  @After
  public void after() {
    mongoTemplate.getDb().drop();
  }

  @Test
  public void addUserTest() {
    String name = "user1";
    User user = mutation.addUser(name);
    assertThat(mongoTemplate.findById(user.getId(), User.class))
        .isNotNull()
        .hasFieldOrPropertyWithValue("name", name);
    System.out.println(user);
  }
}
