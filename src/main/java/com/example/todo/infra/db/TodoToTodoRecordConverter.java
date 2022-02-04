package com.example.todo.infra.db;

import com.example.todo.domain.model.Todo;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class TodoToTodoRecordConverter extends AbstractConverter<Todo, TodoRecord> {

  @Override
  protected TodoRecord convert(Todo source) {
    return TodoRecord.builder()
        .id(source.getId())
        .value(source.getValue())
        .build();
  }
}
