package com.nighthawk.spring_portfolio.mvc.quiz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
public class QuizTaker {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;
    
    /*
     @OneToMany(mappedBy = "quizTaker",cascade = CascadeType.ALL)
     private List<QuizResults> quizResults;
     */
    
   

    //constructor for GET API endpoint
    public QuizTaker(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
    // Initialize static test data 
    // IMPORTANT: data here put in db (see ModelInit.java
    

    public static void main(String[] args) {
}
}