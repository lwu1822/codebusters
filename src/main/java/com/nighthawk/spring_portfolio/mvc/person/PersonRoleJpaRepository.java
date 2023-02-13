package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonRoleJpaRepository extends JpaRepository<PersonRole, Long> {
    PersonRole findByEmail(String email);
}
