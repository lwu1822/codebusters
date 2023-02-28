package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  PersonroleJpaRepository extends JpaRepository<Personrole, Long> {
    Personrole findByEmail(String email);


    List<Personrole> findAllByOrderByEmailAsc();
}
