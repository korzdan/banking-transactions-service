package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
    @Id
    private UUID id;
    private String username;
    private String password;

    public User() {}

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
