package io.bachelors.nwh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.bachelors.nwh.entity.User;

public interface UserRepo extends MongoRepository<User, String> {

    public Optional<User> findByEmail(String email);
}
