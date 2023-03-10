package com.nighthawk.spring_portfolio.mvc.notes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @Column(unique = true)
    private String email;

    // @NotNull
    @Column(columnDefinition = "TEXT")
    private String text;

    public Note(String email, String text) {
        this.email = email;
        this.text = text;
    }

    public String toString() {
        return ("Note: (id=" + this.id + ", email=" + this.email + ", text=" + this.text + ")");
    }

    // Initialize static test data
    // IMPORTANT: data here put in db (see ModelInit.java)
    public static Note[] init() {

        // basics of class construction
        Note p1 = new Note();
        p1.setEmail("toby@gmail.com");
        p1.setText("Note by Toby");

        Note p2 = new Note();
        p2.setEmail("ana@gmail.com");
        p2.setText("Notes from Ana");

        Note p3 = new Note();
        p2.setEmail("Sharon@gmail.com");
        p2.setText("Notes from Sharon");

        Note p4 = new Note();
        p4.setEmail("Tom@gmail.com");
        p4.setText("Notes from Tom");

        // Array definition and data initialization
        Note notes[] = { p1, p2, p3, p4 };
        return (notes);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Note notes[] = init();

        // iterate using "enhanced for loop"
        for (Note note : notes) {
            System.out.println(note); // print object
        }
    }

}