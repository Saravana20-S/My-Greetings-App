package com.bridgelabz.controller;

import com.bridgelabz.model.Greeting;
import com.bridgelabz.service.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;


    // ==============================
    // CREATE GREETING
    // POST /greetings
    // ==============================
    @PostMapping
    public Greeting createGreeting(
            @RequestBody Greeting greeting) {

        greetingService.save(greeting);

        return greeting;
    }


    // ==============================
    // GET GREETING BY ID
    // GET /greetings/{id}
    // ==============================
    @GetMapping("/{id}")
    public Greeting getGreetingById(
            @PathVariable("id") int id) {

        return greetingService.findById(id);
    }


    // ==============================
    // GET ALL GREETINGS
    // GET /greetings
    // ==============================
    @GetMapping
    public List<Greeting> getAllGreetings() {

        return greetingService.findAll();
    }


    // ==============================
    // UPDATE GREETING
    // PUT /greetings/{id}
    // ==============================
    @PutMapping("/{id}")
    public Greeting updateGreeting(
            @PathVariable("id") int id,
            @RequestBody Greeting greeting) {

        greetingService.update(id, greeting);

        return greetingService.findById(id);
    }


    // ==============================
    // DELETE GREETING
    // DELETE /greetings/{id}
    // ==============================
    @DeleteMapping("/{id}")
    public List<Greeting> deleteGreeting(
            @PathVariable("id") int id) {

        greetingService.delete(id);

        return greetingService.findAll();
    }


    // ==============================
    // SEARCH GREETINGS
    // GET /greetings/search?name=Saravanan
    // ==============================
    @GetMapping("/search")
    public List<Greeting> searchGreetings(
            @RequestParam("name") String name) {

        return greetingService.searchByName(name);
    }


    // ==============================
    // GET GREETINGS BY USER
    // GET /greetings/user/{name}
    // ==============================
    @GetMapping("/user/{name}")
    public List<Greeting> getUserGreetings(
            @PathVariable("name") String name) {

        return greetingService.findByUserName(name);
    }
}