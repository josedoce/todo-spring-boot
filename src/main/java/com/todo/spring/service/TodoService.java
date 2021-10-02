package com.todo.spring.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.spring.entity.Todo;
import com.todo.spring.repo.TodoRepo;

@Service
public class TodoService {
	private TodoRepo todoRepo;
	
	@Autowired
	public TodoService(TodoRepo todoRepo) {
		this.todoRepo = todoRepo;
	}
	
	public Map<String, String> saveTodo(Todo todo) {
		Map<String, String> queryParams = new TreeMap<>();
		try {
			this.todoRepo.save(todo);
			queryParams.put("status", "created");
			return queryParams;
		} catch (IllegalArgumentException e) {
			queryParams.put("status", "error_c");
			return queryParams;
		}
	}
	
	public Map<String, String> updateTodo(Integer id, Todo todo) {
		Map<String, String> queryParams = new TreeMap<>();
		try {
			Boolean exist = this.todoRepo.findById(id).isEmpty();
			if(!exist) {
				this.todoRepo.save(todo);
				queryParams.put("status", "edited");
				return queryParams;
			}else {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			queryParams.put("status", "error_e");
			return queryParams;
		}
	}
	
	public List<Todo> getTodos() {
		return this.todoRepo.findAll();
	}
	
	public Map<String, String> deleteTodo(Integer id) {
		Map<String, String> queryParams = new TreeMap<>();
		try {
			this.todoRepo.deleteById(id);	
			queryParams.put("status", "deleted");
			return queryParams;
		} catch (IllegalArgumentException e) {
			queryParams.put("status", "error_d");
			return queryParams;
		}
	}
}
