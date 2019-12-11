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
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo info;

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User() {

    }
}
