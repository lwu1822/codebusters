package com.nighthawk.spring_portfolio.mvc.trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/trivia") // all requests in file begin with this URI
public class TriviaApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily
    // for Database CRUD operations
    @Autowired
    private TriviaJpaRepository repository;

    /*
     * GET List of Jokes
     * 
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific
     * handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Trivia>> getTrivia() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    /*
     * Update Like
     * 
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific
     * handler methods.
     * 
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PostMapping("/like/{id}")
    public ResponseEntity<Trivia> setLike(@PathVariable long id) {
        /*
         * Optional (below) is a container object which helps determine if a result is
         * present.
         * If a value is present, isPresent() will return true
         * get() will return the value.
         */
        Optional<Trivia> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Trivia trivia = optional.get(); // value from findByID
            trivia.setHaha(trivia.getHaha() + 1); // increment value
            repository.save(trivia); // save entity
            return new ResponseEntity<>(trivia, HttpStatus.OK); // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Failed HTTP response: status code, headers, and body
    }

    /*
     * Update Jeer
     */
    @PostMapping("/jeer/{id}")
    public ResponseEntity<Trivia> setJeer(@PathVariable long id) {
        Optional<Trivia> optional = repository.findById(id);
        if (optional.isPresent()) { // Good ID
            Trivia trivia = optional.get();
            trivia.setBoohoo(trivia.getBoohoo() + 1);
            repository.save(trivia);
            
            return new ResponseEntity<>(trivia, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
