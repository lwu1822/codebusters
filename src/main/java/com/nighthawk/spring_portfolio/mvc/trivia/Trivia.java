package com.nighthawk.spring_portfolio.mvc.trivia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trivia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String question;

    private String answer;

    // starting quiz questions
    public static Trivia[] init() {
        final Trivia[] triviaArray = {
                new Trivia(null, "What is the most commonly used letter in the English language?", "e"),
                new Trivia(null,
                        "What is the name of the famous code-breaking machine used by the Allies in World War II?",
                        "enigma"),
                new Trivia(null, "What is the term for a message that has been converted into a secret code?",
                        "cipher"),
                new Trivia(null, "What is the name of the cipher that involves shifting the letters of the alphabet?",
                        "caesar"),
                new Trivia(null, "What is the term for a code that is easily broken or understood?", "weak"),
                new Trivia(null,
                        "What is the term for a code that is difficult or impossible to break without the key?",
                        "strong"),
                new Trivia(null, "What is the name of the cipher that uses a grid to encrypt messages?", "polybius"),
                new Trivia(null, "What is the name of the cipher that involves replacing each letter with a number?",
                        "atbash"),
                new Trivia(null, "What is the term for a code that involves using one word to represent another?",
                        "code word"),
                new Trivia(null,
                        "What is the name of the cipher that involves using a keyword to create a unique encryption?",
                        "vigenere")
        };
        return triviaArray;
    }
}
