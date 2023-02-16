package com.nighthawk.spring_portfolio.mvc.note;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteJpaRepository extends JpaRepository<Note, Long> {
    List<Note> findByPersonId(Long id);

    @Transactional
    void deleteByPersonId(long id);

}
