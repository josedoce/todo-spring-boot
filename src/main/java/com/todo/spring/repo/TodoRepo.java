package com.todo.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.spring.entity.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer>{

}
