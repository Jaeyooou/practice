package com.alchemywiki.server.graphql.resolvers;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import graphql.schema.DataFetchingEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class LogResolverTest {

  @Autowired private MongoTemplate mongoTemplate;

  private LogResolver logResolver;

  private User user = User.builder().name("user1").build();
  private Log log = Log.builder().user(user).description("description").build();

  @Before
  public void before() {
    mongoTemplate.save(user);
    mongoTemplate.save(log);
    logResolver = new LogResolver();
  }

  @After
  public void after() {
    mongoTemplate.getDb().drop();
  }

  @Test
  public void getLogsTest() {
    User author = logResolver.getUser(log);
    assertThat(author.getId()).isEqualTo(user.getId());
  }
}
