package com.springproject.fileparserwebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(id, error.id) && Objects.equals(user, error.user) && Objects.equals(message, error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, message);
    }
}
