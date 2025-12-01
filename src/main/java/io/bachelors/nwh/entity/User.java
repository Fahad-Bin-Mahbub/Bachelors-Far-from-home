package io.bachelors.nwh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@NoArgsConstructor
@Data
@Document("users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String messId;
}
