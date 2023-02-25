package com.nighthawk.spring_portfolio.mvc;

import com.nighthawk.spring_portfolio.mvc.jokes.Jokes;
import com.nighthawk.spring_portfolio.mvc.jokes.JokesJpaRepository;
import com.nighthawk.spring_portfolio.mvc.person.PersonDetailsService;
import com.nighthawk.spring_portfolio.mvc.trivia.Trivia;
import com.nighthawk.spring_portfolio.mvc.trivia.TriviaJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {
    @Autowired
    JokesJpaRepository jokesRepo;
    @Autowired
    PersonDetailsService personService;
    @Autowired
    TriviaJpaRepository triviaRepo;

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

            // Trivia database is populated with starting questions and answers
            Trivia[] triviaArray = Trivia.init();
            for (Trivia trivia : triviaArray) {
                List<Trivia> triviaFound = triviaRepo.findByQuestionIgnoreCase(trivia.getQuestion()); // JPA lookup
                if (triviaFound.size() == 0)
                    triviaRepo.save(new Trivia(null, trivia.getQuestion(), trivia.getAnswer())); // JPA save
            }
        };
    }
}
