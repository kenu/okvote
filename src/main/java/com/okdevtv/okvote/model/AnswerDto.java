package com.okdevtv.okvote.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
