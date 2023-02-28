
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
import com.nighthawk.spring_portfolio.mvc.notes.Note;
import com.nighthawk.spring_portfolio.mvc.notes.NoteJpaRepository;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/log")
public class LogApiController {
    // @Autowired
    // private JwtTokenUtil jwtGen;
    /*
     * #### RESTful API ####
     * Resource: https://spring.io/guides/gs/rest-service/
     */

    // Autowired enables Control to connect POJO Object through JPA
    @Autowired
    private PersonJpaRepository repository;

    @Autowired
    private LogJpaRepository logRepository;

   
    
   @GetMapping("/getlog")
    public ResponseEntity<List<Log>> getLog() {
        return new ResponseEntity<>(logRepository.findAll(), HttpStatus.OK);
        
    } 


}
