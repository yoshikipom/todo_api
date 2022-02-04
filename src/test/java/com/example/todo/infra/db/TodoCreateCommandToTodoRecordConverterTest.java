package com.example.todo.infra.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoCreateCommandToTodoRecordConverterTest {

  @Autowired
  private ModelMapper modelMapper;

  @Test
  void convert_success() {
    var value = "value";
    var input = TodoCreateCommand.builder()
        .value(value)
        .build();

    var actual = modelMapper.map(input, TodoRecord.class);

    assertNull(actual.getId());
    assertEquals(value, actual.getValue());
  }
}
