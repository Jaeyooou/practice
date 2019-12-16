package com.alchemywiki.server.repositories;

import com.alchemywiki.server.entities.Message;
import com.alchemywiki.server.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message , String>{
  List<Message> findByUser(User user);
}