package com.example.todo.infra;

import static java.util.stream.Collectors.toList;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.domain.repository.TodoRepository;
import com.example.todo.infra.db.TodoMapper;
import com.example.todo.infra.db.TodoRecord;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

  private final TodoMapper todoMapper;
  private final ModelMapper modelMapper;

  public TodoRepositoryImpl(TodoMapper todoMapper, ModelMapper modelMapper) {
    this.todoMapper = todoMapper;
    this.modelMapper = modelMapper;
  }

  @Override
  public Todo create(TodoCreateCommand todoCreateCommand) {
    TodoRecord todoRecord = modelMapper.map(todoCreateCommand, TodoRecord.class);
    todoMapper.insert(todoRecord);
    return modelMapper.map(todoRecord, Todo.class);
  }

  @Override
  public List<Todo> readAll() {
    List<TodoRecord> todoRecords = todoMapper.selectAll();
    return todoRecords.stream()
        .map(record -> modelMapper.map(record, Todo.class))
        .collect(toList());
  }

  @Override
  public Todo update(TodoUpdateCommand todoUpdateCommand) {
    TodoRecord todoRecord = modelMapper.map(todoUpdateCommand, TodoRecord.class);
    todoMapper.update(todoRecord);
    return modelMapper.map(todoRecord, Todo.class);
  }

  @Override
  public void delete(int id) {
    todoMapper.delete(id);
  }
}
