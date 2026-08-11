package com.bridgelabz.dao;

import com.bridgelabz.model.Greeting;

import java.util.List;

public interface GreetingDAO {

    int save(Greeting greeting);

    Greeting findById(int id);

    List<Greeting> findAll();

    int update(Greeting greeting);

    int delete(int id);

    List<Greeting> searchByName(String name);

    List<Greeting> findByUserName(String userName);
}