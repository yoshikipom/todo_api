package com.example.todo.infra.db;

import com.example.todo.domain.model.Todo;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class TodoRecordToTodoConverter extends AbstractConverter<TodoRecord, Todo> {

  @Override
  protected Todo convert(TodoRecord source) {
    return Todo.builder()
        .id(source.getId())
        .value(source.getValue())
        .build();
  }
}
