package com.nighthawk.spring_portfolio.mvc.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nighthawk.spring_portfolio.mvc.person.Person;

@Repository
public interface NoteJpaRepository extends JpaRepository<NoteJpaRepository, Long> {

    Note save(Note note);
    // any additional custom query methods can be added here

    void setPerson(Person person);
}
