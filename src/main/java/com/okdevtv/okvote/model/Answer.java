package com.okdevtv.okvote.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    long questionId;
    String answer;

    public Answer(long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer() {

    }

    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }
}
