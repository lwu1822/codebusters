package com.nighthawk.spring_portfolio.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.nighthawk.spring_portfolio.mvc.jokes.Jokes;
import com.nighthawk.spring_portfolio.mvc.jokes.JokesJpaRepository;
import com.nighthawk.spring_portfolio.mvc.notes.Note;
import com.nighthawk.spring_portfolio.mvc.notes.NoteJpaRepository;
//import com.nighthawk.spring_portfolio.mvc.note.Note;
//import com.nighthawk.spring_portfolio.mvc.note.NoteJpaRepository;
import com.nighthawk.spring_portfolio.mvc.person.Person;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;

import java.util.List;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {
    @Autowired
    JokesJpaRepository jokesRepo;
    @Autowired
    NoteJpaRepository noteRepo;
    @Autowired
    PersonDetailsService personService;

    @Bean
    CommandLineRunner run() { // The run() method will be executed after the application starts
        return args -> {

            // Joke database is populated with starting jokes
            String[] jokesArray = Jokes.init();
            for (String joke : jokesArray) {
                List<Jokes> jokeFound = jokesRepo.findByJokeIgnoreCase(joke); // JPA lookup
                if (jokeFound.size() == 0)
                    jokesRepo.save(new Jokes(null, joke, 0, 0)); // JPA save
            }

            // Person database is populated with test data
            Person[] personArray = Person.init();
            for (Person person : personArray) {
                // findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase
                List<Person> personFound = personService.list(person.getName(), person.getEmail()); // lookup
                if (personFound.size() == 0) {
                    personService.save(person); // save

                    // Each "test person" starts with a "test note"
                    String text = "Test " + person.getEmail();
                    Note n = new Note(person.getEmail(), text); // constructor uses new person as Many-to-One
                                                                // association
                    noteRepo.save(n); // JPA Save
                    // testing notes
                }
            }

            /*
             * // Trivia database is populated with starting questions and answers
             * Trivia[] triviaArray = Trivia.init();
             * for (Trivia trivia : triviaArray) {
             * List<Trivia> triviaFound =
             * triviaRepo.findByQuestionIgnoreCase(trivia.getQuestion()); // JPA lookup
             * if (triviaFound.size() == 0)
             * triviaRepo.save(new Trivia(null, trivia.getQuestion(), trivia.getAnswer()));
             * // JPA save
             * }
             */

        };
    }
}