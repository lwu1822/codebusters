// package com.nighthawk.spring_portfolio.mvc.note;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/notes")
// public class NoteController {

//     @Autowired
//     private NoteJpaRepository noteRepository;

//     @GetMapping
//     public ResponseEntity<List<NoteJpaRepository>> getAllNotes() {
//         List<NoteJpaRepository> notes = noteRepository.findAll();
//         return ResponseEntity.ok(notes);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
//         Note note = (Note) noteRepository.findById(id).orElse(null);
//         if (note == null) {
//             return ResponseEntity.notFound().build();
//         }
//         return ResponseEntity.ok(note);
//     }

//     @PostMapping
//     public ResponseEntity<Note> createNote(@RequestBody Note note) {
//         Note createdNote = noteRepository.save(note);
//         return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
//         NoteJpaRepository note = noteRepository.findById(id).orElse(null);
//         if (note == null) {
//             return ResponseEntity.notFound().build();
//         }

//         ((Note) note).setTitle(noteDetails.getTitle());
//         ((Note) note).setContent(noteDetails.getContent());

//         Note updatedNote = (Note) noteRepository.save(note);
//         return ResponseEntity.ok(updatedNote);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<?> deleteNoteById(@PathVariable Long id) {
//         Note note = (Note) noteRepository.findById(id).orElse(null);
//         if (note == null) {
//             return ResponseEntity.notFound().build();
//         }

//         noteRepository.delete((NoteJpaRepository) note);

//         return ResponseEntity.ok().build();
//     }
// }
