package com.nighthawk.spring_portfolio.mvc.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/notes")
public class NoteApiController {

    @Autowired
    private NoteJpaRepository noteRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // get info from cookie so that I can display info on frontend
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
            for (Cookie cookie : cookies) {
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

        // find person object based on email extracted from cookie
        Note note = noteRepository.findByEmail(email);

        System.out.println("Email: " + email);
        System.out.println("Person object: " + note);

        // no feature for change password yet
        // no need String email becuase extract from cookie
        String noteText = note.getText();
        String noteEmail = note.getEmail();

        String finalJson = "{\"email\": \"" + noteEmail + "\",\"note\": \"" + noteText + "\"}";

        return new ResponseEntity<>(finalJson, HttpStatus.OK);

    }

    @PostMapping("/note")
    public Note postNote(@RequestBody Note note) {
        // create a person object to save in the database (along with many to many
        // mapping to roles)

        Note noteReturn = new Note(note.getEmail(), note.getText());
        return noteRepository.save(noteReturn);
    }// test

}
