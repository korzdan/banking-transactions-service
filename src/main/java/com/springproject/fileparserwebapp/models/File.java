package com.springproject.fileparserwebapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(optional = false)
    private User user;

    public File() {}

    public File(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
