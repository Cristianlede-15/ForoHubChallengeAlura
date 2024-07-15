package com.ForoHub.demo.Repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface TopicRepo extends JpaRepository<TopicRepo, Long> {
    @EntityGraph(attributePaths = {"author"})
    @Query("SELECT t FROM topic t")
    List<TopicRepo> findAllTopicsWithAuthors();

    @Query("SELECT COUNT(t) FROM topic t WHERE t.title = :title")
    Long countByTitle(@Param("title") String title);

    @Query("SELECT COUNT(t) FROM topic t WHERE t.body = :body")
    Long countByBody(@Param("body")String body);
}