package com.alchemywiki.server.graphql.resolvers;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import com.alchemywiki.server.repositories.LogRepository;
import graphql.schema.DataFetchingEnvironment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserResolverTest {

  @Autowired private MongoTemplate mongoTemplate;
  @Autowired private LogRepository repository;

  @Mock private DataFetchingEnvironment env;

  private UserResolver userResolver;

  private User user = User.builder().name("user1").build();
  private Log log = Log.builder().user(user).description("description").build();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    mongoTemplate.save(user);
    mongoTemplate.save(log);
    mongoTemplate.save(Log.builder().description("test2").build());
    userResolver = new UserResolver(repository);
  }

  @After
  public void after() {
    mongoTemplate.getDb().drop();
  }

  @Test
  public void getLogsTest() {
    List<Log> logs = userResolver.getLogs(user, env);
    assertThat(logs)
        .hasSize(1)
        .extracting(Log::getId)
        .containsExactlyInAnyOrder(log.getId());
  }
}
