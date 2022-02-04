package com.example.todo.infra.db;

import com.example.todo.domain.model.TodoUpdateCommand;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class TodoUpdateCommandToTodoRecordConverter extends
    AbstractConverter<TodoUpdateCommand, TodoRecord> {

  @Override
  protected TodoRecord convert(TodoUpdateCommand source) {
    return TodoRecord.builder()
        .id(source.getId())
        .value(source.getValue())
        .build();
  }
}
