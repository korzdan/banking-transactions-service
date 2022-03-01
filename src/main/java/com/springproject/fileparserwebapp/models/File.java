package com.springproject.fileparserwebapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinTable(
            name = "users_files",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private User user;

    public File() {}

    public File(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
