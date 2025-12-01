package io.bachelors.nwh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.bachelors.nwh.entity.Mess;

public interface MessRepo extends MongoRepository<Mess, String> {
    
    public Optional<Mess> findByName(String name);

    public Optional<Mess> findByMembers(String member);
}
