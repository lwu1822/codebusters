package com.nighthawk.spring_portfolio.mvc.quiz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */

//may need getter + setter instead of data
//@Data
@Getter 
@Setter
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class QuizResults {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Long quizTakerId;

    // email, password, roles are key attributes to login and authentication
   // @NotEmpty
    private int quizNumber;

   // @NotEmpty
    private int score;	

    private String email;

}