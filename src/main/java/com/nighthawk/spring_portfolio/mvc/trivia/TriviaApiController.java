package com.nighthawk.spring_portfolio.mvc.trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivia")
public class TriviaApiController {

    @Autowired
    private TriviaJpaRepository triviaRepo;

    @GetMapping
    public ResponseEntity<List<Trivia>> getAllTrivia() {
        List<Trivia> triviaList = triviaRepo.findAllByOrderByQuestionAsc();
        return new ResponseEntity<>(triviaList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trivia> getTriviaById(@PathVariable Long id) {
        Trivia trivia = triviaRepo.findById(id).orElse(null);
        if (trivia != null) {
            return new ResponseEntity<>(trivia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Trivia> addTrivia(@RequestBody Trivia trivia) {
        trivia.setId(null); // make sure ID is null to trigger auto-generation
        Trivia newTrivia = triviaRepo.save(trivia);
        return new ResponseEntity<>(newTrivia, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trivia> updateTrivia(@PathVariable Long id, @RequestBody Trivia trivia) {
        Trivia existingTrivia = triviaRepo.findById(id).orElse(null);
        if (existingTrivia != null) {
            existingTrivia.setQuestion(trivia.getQuestion());
            existingTrivia.setAnswer(trivia.getAnswer());
            Trivia updatedTrivia = triviaRepo.save(existingTrivia);
            return new ResponseEntity<>(updatedTrivia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrivia(@PathVariable Long id) {
        triviaRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
