package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  LogJpaRepository extends JpaRepository<Log, Long> {
    Log findByEmail(String email);
}
