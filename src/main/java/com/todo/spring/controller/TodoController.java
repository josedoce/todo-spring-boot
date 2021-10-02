package com.todo.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.spring.entity.Todo;
import com.todo.spring.service.TodoService;

@RestController
public class TodoController {
	private TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@PostMapping("/store")
	public ModelAndView store(Todo todo,  ModelAndView view) {
		view.addAllObjects(this.todoService.saveTodo(todo));
		view.setViewName("redirect:/");
		return view;
	}
	
	@PutMapping("/update/{id}")
	public ModelAndView update(@PathVariable Integer id, Todo todo, ModelAndView view) {
		System.out.println(todo);
		view.addAllObjects(this.todoService.updateTodo(id, todo));
		view.setViewName("redirect:/");
		return view;
	}
	
	@DeleteMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable Integer id, ModelAndView view) {
		view.addAllObjects(this.todoService.deleteTodo(id));
		view.setViewName("redirect:/");
		return view;
	}
}
