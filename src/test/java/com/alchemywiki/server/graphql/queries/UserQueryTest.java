package com.alchemywiki.server.graphql.queries;

import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.exceptions.NotFoundException;
import com.alchemywiki.server.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(SpringRunner.class)
@DataMongoTest
public class UserQueryTest {

  @Autowired private MongoTemplate mongoTemplate;
  @Autowired private UserRepository repository;

  private UserQuery userQuery;

  private User user1 = User.builder().name("user1").build();
  private User user2 = User.builder().name("user2").build();

  @Before
  public void before() {
    mongoTemplate.save(user1);
    mongoTemplate.save(user2);
    userQuery = new UserQuery(repository);
  }

  @After
  public void after() {
    mongoTemplate.getDb().drop();
  }

  @Test
  public void getUserTest() {
    User user = userQuery.getUser(user1.getId());
    assertThat(user.getId()).isEqualTo(user1.getId());
  }

  @Test
  public void getUserFailTest() {
    assertThatThrownBy(() -> userQuery.getUser("INVALID ID"))
        .isInstanceOf(NotFoundException.class);
  }

  @Test
  public void getUsersTest() {
    List<User> users = userQuery.getUsers();
    assertThat(users)
        .extracting(User::getId)
        .hasSize(2)
        .containsExactlyInAnyOrder(user1.getId(), user2.getId());
  }
}
