package com.alchemywiki.server.repositories;

import com.alchemywiki.server.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
