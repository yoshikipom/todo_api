package com.example.todo.infra.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.todo.domain.model.TodoUpdateCommand;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoUpdateCommandToTodoRecordConverterTest {

  @Autowired
  private ModelMapper modelMapper;

  @Test
  void convert_success() {
    var id = 1;
    var value = "value";
    var input = TodoUpdateCommand.builder()
        .id(id)
        .value(value)
        .build();

    var actual = modelMapper.map(input, TodoRecord.class);

    assertEquals(id, actual.getId());
    assertEquals(value, actual.getValue());
  }
}
