package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "errors")
@Getter
@Setter
@ToString
public class Error {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "class")
    private String errorClass;
    private String message;

    public Error() {}

    public Error(String username, String errorClass, String message) {
        this.username = username;
        this.errorClass = errorClass;
        this.message = message;
    }
}
