package com.bridgelabz.service;

import com.bridgelabz.dao.GreetingDAO;
import com.bridgelabz.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    @Autowired
    private GreetingDAO greetingDAO;

    // CREATE
    public int save(Greeting greeting) {

        if (greeting.getUserName() == null || greeting.getUserName().trim().isEmpty()) {

            throw new IllegalArgumentException("User name cannot be empty");
        }

        if (greeting.getMessage() == null || greeting.getMessage().trim().isEmpty()) {

            throw new IllegalArgumentException("Greeting message cannot be empty");
        }

        return greetingDAO.save(greeting);
    }

    // GET BY ID
    public Greeting findById(int id) {

        Greeting greeting = greetingDAO.findById(id);

        if (greeting == null) {

            throw new RuntimeException("Greeting not found with id: " + id);
        }

        return greeting;
    }

    // GET ALL
    public List<Greeting> findAll() {
        return greetingDAO.findAll();
    }

    // UPDATE
    public int update(int id, Greeting greeting) {

        // First check whether greeting exists
        Greeting existingGreeting = greetingDAO.findById(id);

        if (existingGreeting == null) {
            throw new RuntimeException("Greeting not found with id: " + id);
        }

        if (greeting.getUserName() == null || greeting.getUserName().trim().isEmpty()) {

            throw new IllegalArgumentException("User name cannot be empty");
        }

        if (greeting.getMessage() == null || greeting.getMessage().trim().isEmpty()) {

            throw new IllegalArgumentException("Greeting message cannot be empty");
        }

        greeting.setId(id);

        return greetingDAO.update(greeting);
    }

    // DELETE
    public int delete(int id) {

        Greeting greeting = greetingDAO.findById(id);

        if (greeting == null) {

            throw new RuntimeException(
                    "Greeting not found with id: " + id
            );
        }

        return greetingDAO.delete(id);
    }

    // SEARCH BY NAME
    public List<Greeting> searchByName(String name) {

        if (name == null || name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Search name cannot be empty"
            );
        }

        return greetingDAO.searchByName(name);
    }

    // GET GREETINGS BY USER
    public List<Greeting> findByUserName(
            String userName) {

        if (userName == null || userName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "User name cannot be empty"
            );
        }

        return greetingDAO.findByUserName(userName);
    }
}