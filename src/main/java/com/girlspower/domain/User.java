package com.girlspower.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String name;
    private String surname;
    private String birthday;
    private float height;
    private float weight;
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String username, String name, String surname, String birthday,
                float height, float weight, String password) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.username = username;
    }

    public User() {

    }
}
