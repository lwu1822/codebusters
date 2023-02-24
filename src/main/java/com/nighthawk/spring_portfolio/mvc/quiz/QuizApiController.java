package com.nighthawk.spring_portfolio.mvc.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quiz")
public class QuizApiController {
    //     @Autowired
    // private JwtTokenUtil jwtGen;
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
   @Autowired
   private QuizJpaRepository repository;


    /*
    GET List of People
     */
    @GetMapping("/{quizNumber}")
    public ResponseEntity<List<Quiz>> getQuizQuestions(@PathVariable String quizNumber) {

       

        System.out.println("quizNumber="+quizNumber);
        List<Quiz> quizQuestions = repository.findAll();
        System.out.println(quizQuestions);
       

            //return response entity with Person objects in usersList
            return new ResponseEntity<>(quizQuestions, HttpStatus.OK);

    }
    @GetMapping("/init")
    public ResponseEntity<List<Quiz>> init() {

       

        int quizNumber = 1;

        String[] questions = {
        "What is the name of the cipher that involves shifting each letter of the alphabet by a certain number of positions?",
        "What is the name of the cipher that involves replacing each letter with a different letter, number, or symbol according to a specific code?",
        "In the Vigenère cipher, what is the key used to encrypt and decrypt the message?",
        "What is the name of the cipher that involves rearranging the letters of a word or phrase to create a new word or phrase?",
        "What is the name of the cipher that involves substituting each letter of the alphabet with a different letter, number, or symbol based on a set of specific rules?",
        "What is the name of the cipher that involves writing a message in a grid, then reading it off in a specific pattern?",
        "What is the name of the cipher that involves replacing each letter with the letter that is a certain number of positions ahead or behind it in the alphabet?",
        "What is the name of the cipher that involves replacing each letter with the letter that is a certain number of positions ahead or behind it in the alphabet, with the number changing based on the position of the letter in the message?",
        "What is the name of the cipher that involves writing a message backwards and upside down, then rotating it 180 degrees?",
        "What is the name of the cipher that involves replacing each letter with a different letter, number, or symbol based on a specific pattern or key, with the same pattern or key being used for both encryption and decryption?"
        };

        String[] answers = {
        "A", "A", "A", "A", "A", "B", "A", "D", "C", "A"
        };

        Quiz quiz = null;
        for (int index = 0; index < questions.length; index++) {
            quiz  = new Quiz();
            quiz.setQuizNumber(quizNumber);
            quiz.setQuestionNumber(index+1);
            quiz.setQuestion(questions[index]);
            quiz.setAnswer(answers[index]);
            repository.save(quiz);
        }
        
        System.out.println("Quiz Data Initiation Completed");

            //for some reason returning ResponseEntity directly with repository.findAllByOrderByNameAsc does not return a complete
            //Person object, therefore, need to create individual Person objects, add them in a list, and then return them in 
            //ResponseEntity
           
            //debugging
            //System.out.println(usersList); 
            List<Quiz> usersList = new ArrayList<Quiz>();

            //return response entity with Person objects in usersList
            return new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    
}
