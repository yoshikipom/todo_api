package com.example.todo.infra.db;

import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class TodoCreateCommandToTodoRecordConverter extends
    AbstractConverter<TodoCreateCommand, TodoRecord> {

  @Override
  protected TodoRecord convert(TodoCreateCommand source) {
    return TodoRecord.builder()
        .value(source.getValue())
        .build();
  }
}
