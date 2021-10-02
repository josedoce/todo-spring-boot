package com.todo.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.todo.spring.service.TodoService;

@Controller
public class TemplateController {
	private TodoService todoService;
	
	@Autowired
	public TemplateController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("todos", this.todoService.getTodos());
		return "index";
	}
	
}
