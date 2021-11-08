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
public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long answerId;
    public Vote() {}
    public Vote(Long userId, Long answerId) {
        this.userId = userId;
        this.answerId = answerId;
    }

}
