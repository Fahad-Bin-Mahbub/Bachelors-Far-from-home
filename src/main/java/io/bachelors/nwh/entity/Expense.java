package io.bachelors.nwh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@NoArgsConstructor
@Data
@Document("expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userId;

    private Number amount;
    private String description;
    private String category;
    private String date;
}
