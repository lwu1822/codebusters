package com.nighthawk.spring_portfolio.mvc.notes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    Note findByEmail(String email);
}
