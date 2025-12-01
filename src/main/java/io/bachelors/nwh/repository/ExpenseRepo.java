package io.bachelors.nwh.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.bachelors.nwh.entity.Expense;

public interface ExpenseRepo extends MongoRepository<Expense, String> {

    public Optional<Expense> findByUserId(String userId);

    public Optional<Expense> findByUserIdAndDateBetween(String userId, String startDate, String endDate);
}
