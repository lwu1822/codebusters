package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonNoteJpaRepository extends JpaRepository<PersonNote, Long> {
    PersonNote findByEmail(String email);
}
