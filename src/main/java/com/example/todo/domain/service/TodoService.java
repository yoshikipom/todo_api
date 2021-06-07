package com.example.todo.domain.service;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.domain.repository.TodoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public Todo create(TodoCreateCommand todoCreateCommand) {
    return todoRepository.create(todoCreateCommand);
  }

  public List<Todo> readAll() {
    return todoRepository.readAll();
  }

  public Todo update(TodoUpdateCommand todoUpdateCommand) {
    return todoRepository.update(todoUpdateCommand);
  }

  public void delete(int id) {
    todoRepository.delete(id);
  }
}
