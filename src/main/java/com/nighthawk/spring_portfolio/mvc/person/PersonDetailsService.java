package com.nighthawk.spring_portfolio.mvc.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
This class has an instance of Java Persistence API (JPA)
-- @Autowired annotation. Allows Spring to resolve and inject collaborating beans into our bean.
-- Spring Data JPA will generate a proxy instance
-- Below are some CRUD methods that we can use with our database
*/
@Service
@Transactional
public class PersonDetailsService implements UserDetailsService {  // "implements" ties ModelRepo to Spring Security
    // Encapsulate many object into a single Bean (Person, Roles, and Scrum)
    @Autowired  // Inject PersonJpaRepository
    private PersonJpaRepository personJpaRepository;
    @Autowired  // Inject RoleJpaRepository
    private PersonroleJpaRepository personRoleJpaRepository;
    @Autowired  // Inject PasswordEncoder
    private PasswordEncoder passwordEncoder;

    /* UserDetailsService Overrides and maps Person & Roles POJO into Spring Security */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personJpaRepository.findByEmail(email); // setting variable user equal to the method finding the username in the database
        if(person==null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //CHANGE
        person.getPersonrole().forEach(role -> { //loop through roles
            //authorities.add(new SimpleGrantedAuthority(role.getEmail())); //create a SimpleGrantedAuthority by passed in role, adding it all to the authorities list, list of roles gets past in for spring security
             if (role.getRole().equals("user")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
            }
            if (role.getRole().equals("admin")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
            }
            if (role.getRole().equals("anonymous")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ANON"));
            }
        });
        //debugging 
        //CHANGE
        System.out.println("roles: " + person.getPersonrole());
        System.out.println("personAttributes: " + person.getName());
        System.out.println("authorities: " + authorities); 
        // train spring security to User and Authorities
        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), authorities);
    }

    /* Person Section */

    public  List<Person>listAll() {
        return personJpaRepository.findAllByOrderByNameAsc();
    }

    // custom query to find match to name or email
    public  List<Person>list(String name, String email) {
        return personJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(name, email);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Person>listLike(String term) {
        return personJpaRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(term, term);
    }

    // custom query to find anything containing term in name or email ignoring case
    public  List<Person>listLikeNative(String term) {
        String like_term = String.format("%%%s%%",term);  // Like required % rappers
        return personJpaRepository.findByLikeTermNative(like_term);
    }

    // encode password prior to sava
    public void save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personJpaRepository.save(person);
    }

    public Person get(long id) {
        return (personJpaRepository.findById(id).isPresent())
                ? personJpaRepository.findById(id).get()
                : null;
    }

    public Person getByEmail(String email) {
        return (personJpaRepository.findByEmail(email));
    }

    public void delete(long id) {
        personJpaRepository.deleteById(id);
    }

    public void defaults(String password, String roleName) {
        for (Person person: listAll()) {
            if (person.getPassword() == null || person.getPassword().isEmpty() || person.getPassword().isBlank()) {
                person.setPassword(passwordEncoder.encode(password));
            }
            //CHANGE (getRoles)
            if (person.getPersonrole().isEmpty()) {
                Personrole role = personRoleJpaRepository.findByEmail(roleName);
                if (role != null) { // verify role
                    //CHANGE
                    person.getPersonrole().add(role);
                }
            }
        }
    }


    /* Roles Section */

    public void saveRole(Personrole role) {
        Personrole roleObj = personRoleJpaRepository.findByEmail(role.getEmail());
        if (roleObj == null) {  // only add if it is not found
            personRoleJpaRepository.save(role);
        }
    }

    public  List<Personrole>listAllRoles() {
        return personRoleJpaRepository.findAll();
    }

    public Personrole findRole(String roleName) {
        return personRoleJpaRepository.findByEmail(roleName);
    }

    public void addRoleToPerson(String email, String roleName) { // by passing in the two strings you are giving the user that certain role
        Person person = personJpaRepository.findByEmail(email);
        if (person != null) {   // verify person
            Personrole role = personRoleJpaRepository.findByEmail(roleName);
            if (role != null) { // verify role
                boolean addRole = true;
                //CHANGE
                for (Personrole roleObj : person.getPersonrole()) {    // only add if user is missing role
                    if (roleObj.getEmail().equals(roleName)) {
                        addRole = false;
                        break;
                    }
                }
                //CHANGE
                if (addRole) person.getPersonrole().add(role);   // everything is valid for adding role
            }
        }
    }
    
}