package com.nighthawk.spring_portfolio.mvc.person;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import static javax.persistence.FetchType.EAGER;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */

//may need getter + setter instead of data
//@Data
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {

    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String loginStatus; 

    // To be implemented
    //@ManyToMany(fetch = EAGER)

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "person_person_roles",
        joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "person_role_id", referencedColumnName = "id")
    )
    private Set<PersonRole> personrole;

    /* 
    @OneToMany(targetEntity = Log.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private List<Log> logs;
    */

    //maybe other way around?
    /* 
    private Collection<PersonRole> roles = new ArrayList<>();
    */

    //private int score;

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        //this.score = score;
    }

    //constructor for GET API endpoint
    public Person(Long id, String email, String password, String name, Date dob, String loginStatus) {
        this.id = id; 
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.loginStatus = loginStatus; 
    }



    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    //toString because many to many roles may have messed things up (not sure)
    public String toString() {
        return ("Person(id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", name=" + this.name
                + ", dob=" + this.dob + ")");
    }

    // Initialize static test data 
    // IMPORTANT: data here put in db (see ModelInit.java)
    public static Person[] init() {
        
        // basics of class construction
        Person p1 = new Person();
        p1.setName("Thomas Edison");
        p1.setEmail("toby@gmail.com");
        p1.setPassword("123Toby!");
        // adding Note to notes collection
        try {  // All data that converts formats could fail
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1840");
            p1.setDob(d);
        } catch (Exception e) {
            // no actions as dob default is good enough
        }

        Person p2 = new Person();
        p2.setName("Alexander Graham Bell");
        p2.setEmail("lexb@gmail.com");
        p2.setPassword("123LexB!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1845");
            p2.setDob(d);
        } catch (Exception e) {
        }

        Person p3 = new Person();
        p3.setName("Nikola Tesla");
        p3.setEmail("niko@gmail.com");
        p3.setPassword("123Niko!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1850");
            p3.setDob(d);
        } catch (Exception e) {
        }

        Person p4 = new Person();
        p4.setName("Madam Currie");
        p4.setEmail("madam@gmail.com");
        p4.setPassword("123Madam!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("01-01-1860");
            p4.setDob(d);
        } catch (Exception e) {
        }

        Person p5 = new Person();
        p5.setName("John Mortensen");
        p5.setEmail("jm1021@gmail.com");
        p5.setPassword("123Qwerty!");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("10-21-1959");
            p5.setDob(d);
        } catch (Exception e) {
        }


        Person p6 = new Person();
        p6.setName("a a");
        p6.setEmail("a@gmail.com");
        p6.setPassword("a");
        try {
            Date d = new SimpleDateFormat("MM-dd-yyyy").parse("02-15-2023");
            p6.setDob(d);
        } catch (Exception e) {
        }

        // Array definition and data initialization
        Person persons[] = {p1, p2, p3, p4, p5, p6};
        return(persons);
    }

    public static void main(String[] args) {
        // obtain Person from initializer
        Person persons[] = init();

        // iterate using "enhanced for loop"
        for( Person person : persons) {
            System.out.println(person);  // print object
        }
    }

}