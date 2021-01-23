package com.placetalkr.prototype.repository;

import com.placetalkr.prototype.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
}
