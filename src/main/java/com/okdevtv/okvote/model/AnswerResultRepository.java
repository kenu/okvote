package com.okdevtv.okvote.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class AnswerResultRepository {
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    public List<AnswerDto> findAnswerResult(Long questionId) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createQuery(
                "select new com.okdevtv.okvote.model.AnswerDto(a.id, a.questionId, a.answer, count(v.answerId)) " +
                        "from Vote v " +
                        "right join Answer a on a.id = v.answerId " +
                        "inner join Question q on q.id = a.questionId " +
                        "where q.id = :questionId " +
                        "group by a.id", AnswerDto.class  )
                .setParameter("questionId", questionId)
                .getResultList();
    }
}
