package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  LoginJpaRepository extends JpaRepository<Login, Long> {
    //search login status based on userid
    Login findByUserId(Long userId);
}
