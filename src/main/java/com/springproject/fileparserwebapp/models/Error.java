package com.springproject.fileparserwebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "users_errors",
            joinColumns = @JoinColumn(name = "error_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private User user;
    private String message;

    public Error() {}

    public Error(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
