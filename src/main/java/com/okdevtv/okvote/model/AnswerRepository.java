package com.okdevtv.okvote.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "SELECT id, question_id, answer FROM answer a WHERE a.question_id = :questionId",
            nativeQuery = true)
    List<Answer> findByQuestionId(@Param("questionId") Long questionId);

}
