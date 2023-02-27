package com.nighthawk.spring_portfolio.mvc.quiz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/*
Extends the JpaRepository interface from Spring Data JPA.
-- Java Persistent API (JPA) - Hibernate: map, store, update and retrieve database
-- JpaRepository defines standard CRUD methods
-- Via JPA the developer can retrieve database from relational databases to Java objects and vice versa.
 */
public interface QuizResultsJpaRepository extends JpaRepository<QuizResults, Long> {

   QuizResults findByQuizTakerIdAndQuizNumber(Long quizTakerId, int quizNumber);
   List<QuizResults> findByQuizNumber(int quizNumber);
}