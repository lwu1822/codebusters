package com.nighthawk.spring_portfolio.mvc.quiz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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
public class Quiz {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // email, password, roles are key attributes to login and authentication
   // @NotEmpty
    private int quizNumber;

   // @NotEmpty
    private int questionNumber;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    private String question;
    @NonNull
    private String answer;

    @NonNull
    private String[] quizOptions;

    public Quiz(int id, @NotEmpty int quizNumber, @NotEmpty int questionNumber, @NonNull String question,
            @NonNull String answer) {
        this.id = id;
        this.quizNumber = quizNumber;
        this.questionNumber = questionNumber;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getQuizNumber() {
        return quizNumber;
    }
    
    public void setQuzNumber(int quizNumber) {
        this.quizNumber = quizNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }
    
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getQuizOptions(){

        return quizOptions;
    }

    public void setQuizOptions(String[] quizOptions){
        this.quizOptions = quizOptions;
    }

    public static void main(String[] args) {
       
     }
    // Initialize static test data 
    // IMPORTANT: data here put in db (see ModelInit.java)


}