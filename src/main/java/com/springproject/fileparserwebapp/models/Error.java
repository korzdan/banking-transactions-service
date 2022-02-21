package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "errors")
@Getter
@Setter
public class Error {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    private String message;

    public Error() {}

    public Error(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
