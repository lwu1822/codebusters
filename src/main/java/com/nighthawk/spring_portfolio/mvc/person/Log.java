package com.nighthawk.spring_portfolio.mvc.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


import javax.persistence.ManyToMany;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

// 2nd + 3rd anotation: mk constructor w/ no args + arg
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //change name to email because email is a unique identifier 

    @Column(unique=true)
    private String email;

    @NotEmpty
    private String log;

    private Long userId;

    public Log(String email, String log) {
        this.email = email; 
        this.log = log; 
    }
}
