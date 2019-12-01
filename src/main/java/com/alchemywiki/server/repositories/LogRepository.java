package com.alchemywiki.server.repositories;

import com.alchemywiki.server.entities.Log;
import com.alchemywiki.server.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, String> {
  List<Log> findByUser(User user);
}
