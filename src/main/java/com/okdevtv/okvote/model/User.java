package com.okdevtv.okvote.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    String name;

    public User(String name) {
        this.name = name;
    }

    public User() {}

}
