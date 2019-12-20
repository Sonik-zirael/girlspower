package com.girlspower.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String time;
    private String text;
    private String mainProducts;

    public Receipt() {

    }

    public Receipt(String title, String time, String text, String mainProducts) {
        this.title = title;
        this.time = time;
        this.text = text;
        this.mainProducts = mainProducts;
    }
}
