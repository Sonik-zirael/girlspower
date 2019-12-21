package com.girlspower.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Float weight;
    private Float height;
    private Float aim;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder(toBuilder = true)
    public UserInfo(String firstName, String lastName, Date birthday,
                    User user, Float weight, Float height, Float aim) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.user = user;
        this.aim = aim;
    }

    public UserInfo() {

    }
}
