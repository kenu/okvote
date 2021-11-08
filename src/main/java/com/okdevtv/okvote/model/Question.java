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
public class Question {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    long userId;
    String question;

    public Question() {
    }

    public Question(Long userId, String question) {
        this.userId = userId;
        this.question = question;
    }

}
