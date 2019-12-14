package com.girlspower.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private float weight;
    private float height;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    private User owner;

    Statistics() {

    }

    public Statistics(Date date, float weight, float height, User user) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.owner = user;
    }
}
