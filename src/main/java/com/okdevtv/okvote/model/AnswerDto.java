package com.okdevtv.okvote.model;

public class AnswerDto {
    Long id;
    Long questionId;
    String answer;
    Long cnt;
    Long percent;

    public AnswerDto(Long id, Long questionId, String answer, Long cnt) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.cnt = cnt;
    }

    public AnswerDto() {}

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public Long getCnt() {
        return cnt;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
