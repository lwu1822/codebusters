package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.text.SimpleDateFormat;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nighthawk.spring_portfolio.mvc.jwt.JwtTokenUtil;


import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/person")
public class PersonApiController {
    //     @Autowired
    // private JwtTokenUtil jwtGen;
    /*
    #### RESTful API ####
    Resource: https://spring.io/guides/gs/rest-service/
    */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private PersonJpaRepository repository;

    @Autowired
    private PersonRoleJpaRepository roleRepository; 

    //note: if no do autowired, will return null
    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    /*
    GET List of People
     */
    @GetMapping("/")
    public ResponseEntity<List<Person>> getPeople() {


            List<Person> users = repository.findAllByOrderByNameAsc(); 

            System.out.println(users);

            //for some reason returning ResponseEntity directly with repository.findAllByOrderByNameAsc does not return a complete
            //Person object, therefore, need to create individual Person objects, add them in a list, and then return them in 
            //ResponseEntity
            List<Person> usersList = new ArrayList<Person>();

            for (int i = 0; i < users.size(); i++) {
                //find all the attributes of Person object
                Long id = users.get(i).getId(); 
                String email = users.get(i).getEmail(); 
                String password = users.get(i).getPassword(); 
                String name = users.get(i).getName(); 
                Date dob = users.get(i).getDob(); 

                //make a new person object with the attributes found above
                Person person = new Person(id, email, password, name, dob); 

                //add the person object into the usersList
                usersList.add(person); 
            }
            
            //debugging
            //System.out.println(usersList); 
           
            //return response entity with Person objects in usersList
            return new ResponseEntity<>(usersList, HttpStatus.OK);

    }

    //get info from cookie so that I can display info on frontend
    @GetMapping("/findEmail")
    public ResponseEntity<String> cookieTest(HttpServletRequest request) {
        final Cookie[] cookies = request.getCookies();
        System.out.println(cookies);

        String email = null;
		String jwtToken = null;
		// Try to get cookie with name jwt
		if ((cookies == null) || (cookies.length == 0)) {
			System.out.println("No cookies");
		} else {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals("jwt")) {
					jwtToken = cookie.getValue();
                    System.out.println("JWTTOKEN: " + jwtToken);
				}
			}
			if (jwtToken == null) {
				System.out.println("No jwt cookie");
			} else {
				try {
					// Get email from the token if jwt cookie exists
					email = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					System.out.println("JWT Token has expired");
				} catch (Exception e) {
					System.out.println("An error occurred");
				}
                
			}
        }

        //find person object based on email extracted from cookie
        Person person = repository.findByEmail(email); 

        System.out.println("Email: " + email); 
        System.out.println("Person object: " + person); 

        //no feature for change password yet
        //no need String email becuase extract from cookie
        String name = person.getName(); 
        Date dob = person.getDob(); 
        String id = String.valueOf(person.getId());


        String finalJson = "{\"email\": \"" + email + "\",\"name\": \"" + name + "\",\"dob\": \"" + dob + "\",\"id\": \"" + id +"\"}"; 

        return new ResponseEntity<>(finalJson, HttpStatus.OK);

    }

    /*
    GET individual Person using ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        System.out.println(optional);
        if (optional.isPresent()) {  // Good ID
             //refer to comments above to see why code was changed
            
            //find all the attributes of Person object
            String email = optional.get().getEmail(); 
            String password = optional.get().getPassword(); 
            String name = optional.get().getName(); 
            Date dob = optional.get().getDob(); 

            //make a new person object with the attributes found above
            Person person = new Person(id, email, password, name, dob); 

            //add the person object into the usersList
            //this doesn't work for some reason
            //Person person = optional.get();  // value from findByID
            
            return new ResponseEntity<>(person, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);       
        
    }

    /*
    DELETE individual Person using ID
     */
    /* 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
            return new ResponseEntity<>(person, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
    */

    /* 
    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID
            repository.deleteById(id);  // value from findByID
        }
        // Bad ID
    }
    */

    @GetMapping("/delete/{id}")
    public void deletePerson(@PathVariable long id) {
        Optional<Person> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            repository.deleteById(id);  // value from findByID
        }
        // Bad ID
    }

    /*
    POST Aa record by Requesting Parameters from URI
     */

    //CHANGED TO USING TEXT (JSON) TO CREATE A USER SO THAT THE PERSON'S ROLE CAN ALSO BE AN INPUT
    /* 
    @PostMapping( "/post")
    public ResponseEntity<Object> postPerson(@RequestParam("email") String email,
                                             @RequestParam("password") String password,
                                             @RequestParam("name") String name,
                                             @RequestParam("dob") String dobString,
                                             @RequestParam("score") int score) {
        Date dob;
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        try {
            dob = new SimpleDateFormat("MM-dd-yyyy").parse(dobString);
        } catch (Exception e) {
            return new ResponseEntity<>(dobString +" error; try MM-dd-yyyy", HttpStatus.BAD_REQUEST);
        }
        // A person object WITHOUT ID will create a new record with default roles as student
        Person person = new Person(email, password, name, dob, score);
        repository.save(person);
        PersonRole personRole = new PersonRole(name, "user");
        roleRepository.save(personRole); 
        return new ResponseEntity<>(email +" is created successfully", HttpStatus.CREATED);
    }
    */

    //this is the new endpoint for creating a user (uses many to many to connect to roles)
    @PostMapping( "/post")
    public Person postPerson(@RequestBody Person person) {
        //encrypt password
        String password = person.getPassword(); 
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        //create a person object to save in the database (along with many to many mapping to roles)
        Person personReturn = new Person(person.getId(), person.getEmail(), password, person.getName(), person.getDob(), person.getPersonrole(), person.getLogs(), null);
        return repository.save(personReturn); 
    }

    /*
    The personSearch API looks across database for partial match to term (k,v) passed by RequestEntity body
     */
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> personSearch(@RequestBody final Map<String,String> map) {
        // extract term from RequestEntity
        String term = (String) map.get("term");

        // JPA query to filter on term
        List<Person> list = repository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);

        // return resulting list and status, error checking should be added
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*
    The personStats API adds stats by Date to Person table 
    */
    @PostMapping(value = "/setStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> personStats(@RequestBody final Map<String,Object> stat_map) {
        // find ID
        long id=Long.parseLong((String)stat_map.get("id"));  
        Optional<Person> optional = repository.findById((id));
        if (optional.isPresent()) {  // Good ID
            Person person = optional.get();  // value from findByID

            // Extract Attributes from JSON
            Map<String, Object> attributeMap = new HashMap<>();
            for (Map.Entry<String,Object> entry : stat_map.entrySet())  {
                // Add all attribute other thaN "date" to the "attribute_map"
                if (!entry.getKey().equals("date") && !entry.getKey().equals("id"))
                    attributeMap.put(entry.getKey(), entry.getValue());
            }

            // Set Date and Attributes to SQL HashMap
            Map<String, Map<String, Object>> date_map = new HashMap<>();
            date_map.put( (String) stat_map.get("date"), attributeMap );
            person.setStats(date_map);  // BUG, needs to be customized to replace if existing or append if new
            repository.save(person);  // conclude by writing the stats updates

            // return Person with update Stats
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        // return Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
    }
}
