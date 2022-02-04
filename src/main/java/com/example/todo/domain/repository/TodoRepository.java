package com.example.todo.domain.repository;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import java.util.List;

public interface TodoRepository {

  Todo create(TodoCreateCommand todoCreateCommand);
  List<Todo> readAll();
  Todo update(TodoUpdateCommand todoUpdateCommand);
  void delete(int id);
}
