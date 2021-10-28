package com.okdevtv.okvote.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT id, name FROM User u WHERE u.name = :name",
            nativeQuery = true)
    User findUserByName(@Param("name") String name);
}
