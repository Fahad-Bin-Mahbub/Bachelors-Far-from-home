package io.bachelors.nwh.entity;

import java.util.ArrayList;

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
@Document("mess")
public class Mess {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private ArrayList<String> members = new ArrayList<>();
}
