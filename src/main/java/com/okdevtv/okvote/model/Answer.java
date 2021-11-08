package com.okdevtv.okvote.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    Long questionId;
    String answer;

    public Answer(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer() {

    }

}
