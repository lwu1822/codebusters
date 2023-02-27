package com.nighthawk.spring_portfolio.mvc.quiz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/quiz")
public class QuizApiController {
    // @Autowired
    // private JwtTokenUtil jwtGen;
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private QuizJpaRepository quizRepository;

    @Autowired
    private QuizTakerJpaRepository quizTakerRepository;

    @Autowired
    private QuizResultsJpaRepository quizResultsRepository;

    /*
     * GET List of People
     */
    @GetMapping("/{quizNumber}")
    public ResponseEntity<List<Quiz>> getQuizQuestions(@PathVariable String quizNumber) {

        System.out.println("quizNumber=" + quizNumber);
        List<Quiz> quizQuestions = quizRepository.findAll();
        System.out.println(quizQuestions);

        // return response entity with Person objects in usersList
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);

    }

    /*
     * GET List of People
     */
    @GetMapping("/{quizNumber}/{questionNumber}")
    public ResponseEntity<Quiz> getQuizQuestion(@PathVariable String quizNumber,
            @PathVariable String questionNumber) {

        System.out.println("quizNumber=" + quizNumber);
        System.out.println("questionNumber=" + questionNumber);
        Integer quizNumberInt = Integer.valueOf(quizNumber);
        Integer questionNumberInt = Integer.valueOf(questionNumber);

        Quiz quiz = quizRepository.findByQuizNumberAndQuestionNumber(quizNumberInt, questionNumberInt);
        System.out.println(quiz);

        // return response entity with Person objects in usersList
        return new ResponseEntity<>(quiz, HttpStatus.OK);

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

        String[][] quizOptionsArray = {
                {
                        "A. Caesar cipher",
                        "B. Vigenère cipher",
                        "C. Rail fence cipher",
                        "D. Atbash cipher"
                },
                {
                        "A. Substitution cipher",
                        "B. Transposition cipher",
                        "C. Playfair cipher",
                        "D. Enigma cipher"
                },
                {
                        "A. A keyword",
                        "B. A shift",
                        "C. A matrix",
                        "D. A hash function"
                },
                {
                        "A. Anagram cipher",
                        "B. Rail fence cipher",
                        "C. Atbash cipher",
                        "D. Caesar cipher"
                },
                {
                        "A. Substitution cipher",
                        "B. Transposition cipher",
                        "C. Playfair cipher",
                        "D. Enigma cipher"
                },

                {
                        "A. Rail fence cipher",
                        "B. Columnar transposition cipher",
                        "C. Playfair cipher",
                        "D. Atbash cipher"
                },

                {
                        "A. Caesar cipher",
                        "B. Vigenère cipher",
                        "C. Rail fence cipher",
                        "D. Atbash cipher"
                },

                {
                        "A. Beaufort cipher",
                        "B. Polybius cipher",
                        "C. Atbash cipher",
                        "D. Autokey cipher"
                },

                {
                        "A. Bacons cipher",
                        "B. Playfair cipher",
                        "C. Mirror cipher",
                        "D. Scytale cipher"
                },

                {
                        "A. Vernam cipher",
                        "B. Rail fence cipher",
                        "C. Atbash cipher",
                        "D. Caesar cipher"
                }

        };
        String[] answers = {
                "A", "A", "A", "A", "A", "B", "A", "D", "C", "A"
        };

        Quiz quiz = null;
        for (int index = 0; index < questions.length; index++) {
            quiz = new Quiz();
            quiz.setQuizNumber(quizNumber);
            quiz.setQuestionNumber(index + 1);
            quiz.setQuestion(questions[index]);
            quiz.setQuizOptions(quizOptionsArray[index]);
            quiz.setAnswer(answers[index]);
            quizRepository.save(quiz);
        }

        System.out.println("Quiz Data Initiation Completed");

        // for some reason returning ResponseEntity directly with
        // repository.findAllByOrderByNameAsc does not return a complete
        // Person object, therefore, need to create individual Person objects, add them
        // in a list, and then return them in
        // ResponseEntity

        // debugging
        // System.out.println(usersList);
        List<Quiz> quizQuestions = new ArrayList<Quiz>();

        // return response entity with Person objects in usersList
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);

    }

    @PostMapping("/quizTaker")
    public ResponseEntity<String> createQuizTaker(@RequestBody QuizTaker quizTaker) {
        // encrypt password

        // create a person object to save in the database (along with many to many
        // mapping to roles)
        Map<String, Object> response = new HashMap<>();
        try {

            String email = quizTaker.getEmail();
            QuizTaker existingQT = quizTakerRepository.findByEmail(email);
            System.out.println(existingQT);
            if (existingQT != null) {
                response.put("status", "error");
                response.put("message", "The QuizTaker " + email + " already exists in the system");

            } else {
                quizTakerRepository.save(quizTaker);
                response.put("status", "success");
                response.put("message", "Quiz Taker saved successfully");
                return ResponseEntity.ok("QuizTaker saved Successfully");
            }
            String json = new ObjectMapper().writeValueAsString(response);

            return ResponseEntity.ok(json);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Faied to save the QuizTaker " + e.getMessage());
        }

    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAnswer(@RequestBody QuizAnswerData quizAnswerData) {
        // encrypt password

        // create a person object to save in the database (along with many to many
        // mapping to roles)
        try {

            String answer = quizAnswerData.getAnswer();
            Integer quizNumber = Integer.valueOf(quizAnswerData.getQuizNumber());
            Integer quizQuestionNumber = Integer.valueOf(quizAnswerData.getQuestionNumber());
            Quiz quizRecord = quizRepository.findByQuizNumberAndQuestionNumber(quizNumber, quizQuestionNumber);

            // Find the QuizTaker
            QuizTaker quizTaker = quizTakerRepository.findByEmail(quizAnswerData.getEmail());

            Map<String, Object> response = new HashMap<>();

            if (quizRecord != null && quizTaker != null) {

                // Check the answer
                String correctAnswer = quizRecord.getAnswer();
                QuizResults quizResults = null;
                if (correctAnswer.equalsIgnoreCase(answer)) {
                    System.out
                            .println("Submitted answer " + answer + " is correct for quizTakerId " + quizTaker.getId());

                    // See if the record exist for this user
                    quizResults = quizResultsRepository.findByQuizTakerIdAndQuizNumber(quizTaker.getId(),
                            quizRecord.getQuizNumber());

                    if (quizResults != null) {
                        quizResults.setScore(quizResults.getScore() + 1);
                    } else {
                        quizResults = new QuizResults();
                        quizResults.setScore(1);
                    }

                    quizResults.setQuizNumber(quizRecord.getQuizNumber());
                    quizResults.setQuizTakerId(quizTaker.getId());
                    quizResults.setEmail(quizTaker.getEmail());
                    quizResultsRepository.save(quizResults);

                }
                response.put("status", "success");
                response.put("message", "Your answer saved successfully");
            } else {
                response.put("status", "error");
                response.put("message", "Quiz record or Quiz Taker doesn't exist in the system");

            }

            String json = new ObjectMapper().writeValueAsString(response);

            return ResponseEntity.ok(json);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Faied to save your answer " + e.getMessage());
        }

    }

    /*
     * GET List of People
     */
    @GetMapping("/{quizNumber}/results")
    public ResponseEntity<List<QuizResults>> getQuizResults(@PathVariable Integer quizNumber) {

        System.out.println("quizNumber=" + quizNumber);
        List<QuizResults> quizResults = quizResultsRepository.findByQuizNumber(quizNumber.intValue());
        // return response entity with Person objects in usersList

        return new ResponseEntity<>(quizResults, HttpStatus.OK);

    }

}
