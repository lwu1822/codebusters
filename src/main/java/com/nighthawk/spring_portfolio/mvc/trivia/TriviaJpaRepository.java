package com.nighthawk.spring_portfolio.mvc.trivia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TriviaJpaRepository extends JpaRepository<Trivia, Long> {
    void save(String question, String answer);

    List<Trivia> findAllByOrderByQuestionAsc();

    List<Trivia> findByQuestionIgnoreCase(String question);
}
