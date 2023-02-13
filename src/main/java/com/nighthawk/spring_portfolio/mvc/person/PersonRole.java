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

// 2nd + 3rd anotation: mk constructor w/ no args + arg
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PersonRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;

    @NotEmpty
    private String role;

    @ManyToMany(mappedBy = "personrole", fetch = FetchType.LAZY)
    private Set<Person> person; 


    public PersonRole(String name, String role) {
        this.name = name; 
        this.role = role; 
    }
}
