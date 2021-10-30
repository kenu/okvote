package com.okdevtv.okvote.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public long getId() {
        return id;
    }
    public long getUserId() {
        return userId;
    }

    public String getQuestion() {
        return question;
    }
}
