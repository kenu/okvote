package com.okdevtv.okvote.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "SELECT id, question_id, answer FROM answer a WHERE a.question_id = :questionId",
            nativeQuery = true)
    List<Answer> findByQuestionId(@Param("questionId") Long questionId);

    @Query(value = "select a.id, a.question_id, a.answer, count(v.answer_id) cnt " +
            "from vote v " +
            "right join answer a on a.id = v.answer_id " +
            "inner join question q on q.id = a.question_id " +
            "where q.id = :questionId " +
            "group by a.id;",
            nativeQuery = true)
    List<Answer> findByQuestionIdWithCnt(@Param("questionId") Long questionId);

}
