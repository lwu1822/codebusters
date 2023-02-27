package com.nighthawk.spring_portfolio.mvc.notes;

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
@RequestMapping("/api/notes")
public class NoteApiController {

    @Autowired
    private NoteJpaRepository noteRepository;

    @PostMapping("/note")
    public Note postNote(@RequestBody Note note) {
        // create a person object to save in the database (along with many to many
        // mapping to roles)

        Note noteReturn = new Note(note.getEmail(), note.getText());
        return noteRepository.save(noteReturn);
    }// test

}
