package com.okdevtv.okvote.model;


import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    Long questionId;
    String answer;
    @Transient
    Integer cnt;
    @Transient
    Integer percent;

    public Answer(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Answer() {

    }

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public Integer getCnt() {
        return cnt;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
