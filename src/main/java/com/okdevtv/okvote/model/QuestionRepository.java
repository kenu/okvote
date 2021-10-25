package com.okdevtv.okvote.model;

import com.okdevtv.okvote.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
