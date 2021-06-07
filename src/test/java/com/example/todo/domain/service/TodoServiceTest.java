package com.example.todo.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.domain.repository.TodoRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

  private TodoService target;

  @Mock
  private TodoRepository todoRepository;

  @Mock
  private Todo todoMock;

  @BeforeEach
  void setUp() {
    target = new TodoService(todoRepository);
  }

  @Test
  void create_success() {
    TodoCreateCommand todoCreateCommand = mock(TodoCreateCommand.class);
    when(todoRepository.create(todoCreateCommand)).thenReturn(todoMock);

    var actual = target.create(todoCreateCommand);

    assertEquals(todoMock, actual);
  }

  @Test
  void readAll_success() {
    when(todoRepository.readAll()).thenReturn(List.of(todoMock));

    var actual = target.readAll();

    assertEquals(1, actual.size());
    assertEquals(todoMock, actual.get(0));
  }

  @Test
  void update_success() {
    TodoUpdateCommand todoUpdateCommand = mock(TodoUpdateCommand.class);
    when(todoRepository.update(todoUpdateCommand)).thenReturn(todoMock);

    var actual = target.update(todoUpdateCommand);

    assertEquals(todoMock, actual);
  }

  @Test
  void delete_success() {
    int id = 1;
    doNothing().when(todoRepository).delete(id);

    target.delete(id);

    verify(todoRepository).delete(id);
  }
}
