package io.bachelors.nwh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.bachelors.nwh.entity.Earning;

public interface EarningRepo extends MongoRepository<Earning, String> {

    public Optional<Earning> findByUserId(String userId);

    public Optional<Earning> findByUserIdAndDateBetween(String userId, String startDate, String endDate);
}
