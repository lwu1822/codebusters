package com.nighthawk.spring_portfolio.mvc.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


import javax.persistence.ManyToMany;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

// 2nd + 3rd anotation: mk constructor w/ no args + arg
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //change name to email because email is a unique identifier 

    @Column(unique=true)
    private String email;

    private String plaintext;
    
    private String ciphertext;

    private Long userId;

    public Log(String email, String plaintext, String ciphertext) {
        this.email = email; 
        this.plaintext = plaintext; 
        this.ciphertext = ciphertext; 
    }

    public String toString() {
        return ("Log: (id=" + this.id + ", email=" + this.email + ", plaintext=" + this.plaintext + ", ciphertext=" + this.ciphertext + ")");
    }

    // Initialize static test data 
    // IMPORTANT: data here put in db (see ModelInit.java)
    public static Log[] init() {
        
        // basics of class construction
        Log p1 = new Log();
        p1.setEmail("toby@gmail.com");
        p1.setPlaintext("123Toby!");
        

        Log p2 = new Log();
        p2.setEmail("lexb@gmail.com");
        p2.setPlaintext("123LexB!");
        

        Log p3 = new Log();
        p3.setEmail("niko@gmail.com");
        p3.setPlaintext("123Niko!");
        

        Log p4 = new Log();
        p4.setEmail("madam@gmail.com");
        p4.setPlaintext("123Madam!");
        

        Log p5 = new Log();
        p5.setEmail("jm1021@gmail.com");
        p5.setPlaintext("123Qwerty!");
        
        

        // Array definition and data initialization
        Log logs[] = {p1, p2, p3, p4, p5};
        return(logs);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Log logs[] = init();

        // iterate using "enhanced for loop"
        for( Log log : logs) {
            System.out.println(log); // print object
        }
    }

}

