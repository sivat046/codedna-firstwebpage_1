package com.example.codednafirstwebpage.service;

import com.example.codednafirstwebpage.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    //retrieve data
    List<Todo> findByUser(String user);
}
