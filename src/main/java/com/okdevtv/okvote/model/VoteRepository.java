package com.okdevtv.okvote.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends PagingAndSortingRepository<Vote, Long> {
	@Query(value = "select * from Vote v where v.user_id = :userId and v.answer_id = :answerId",
	nativeQuery = true)
	Vote findByUserIdAnswerId(@Param("userId") Long userId, @Param("answerId") Long answerId);
}
