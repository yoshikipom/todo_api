package com.example.todo.infra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.infra.db.TodoMapper;
import com.example.todo.infra.db.TodoRecord;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class TodoRepositoryImplTest {

  private TodoRepositoryImpl target;
  @Mock
  private TodoMapper todoMapper;
  @Mock
  private ModelMapper modelMapper;

  @Mock
  private TodoUpdateCommand todoUpdateCommand;
  @Mock
  private TodoCreateCommand todoCreateCommand;
  @Mock
  private TodoRecord todoRecord;
  @Mock
  private Todo todo;

  @BeforeEach
  void setUp() {
    target = new TodoRepositoryImpl(todoMapper, modelMapper);
  }

  @Test
  void create_success() {
    when(modelMapper.map(todoCreateCommand, TodoRecord.class)).thenReturn(todoRecord);
    doNothing().when(todoMapper).insert(todoRecord);
    when(modelMapper.map(todoRecord, Todo.class)).thenReturn(todo);

    var actual = target.create(todoCreateCommand);

    assertEquals(todo, actual);
    verify(todoMapper).insert(todoRecord);
  }

  @Test
  void readAll_success() {
    when(todoMapper.selectAll()).thenReturn(List.of(todoRecord));
    when(modelMapper.map(todoRecord, Todo.class)).thenReturn(todo);

    var actual = target.readAll();

    assertEquals(1, actual.size());
    assertEquals(todo, actual.get(0));
  }

  @Test
  void update_success() {
    when(modelMapper.map(todoUpdateCommand, TodoRecord.class)).thenReturn(todoRecord);
    doNothing().when(todoMapper).update(todoRecord);
    when(modelMapper.map(todoRecord, Todo.class)).thenReturn(todo);

    var actual = target.update(todoUpdateCommand);

    assertEquals(todo, actual);
    verify(todoMapper).update(todoRecord);
  }

  @Test
  void delete_success() {
    int id = 1;
    doNothing().when(todoMapper).delete(id);

    target.delete(id);

    verify(todoMapper).delete(id);
  }
}
