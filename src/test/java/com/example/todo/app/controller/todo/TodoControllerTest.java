package com.example.todo.app.controller.todo;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.domain.service.TodoService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

  private static final String BASE_PATH = "/todos";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TodoService todoService;

  @MockBean
  private ModelMapper modelMapper;

  @Mock
  private Todo todo;

  private TodoResponse todoResponse = TodoResponse.builder()
      .id(0)
      .value("value")
      .build();

  @BeforeEach
  void setUp() {
    when(modelMapper.map(any(Todo.class), eq(TodoResponse.class))).thenReturn(todoResponse);
  }

  @Test
  void post_success() throws Exception {
    var todoCreateCommand = mock(TodoCreateCommand.class);
    when(modelMapper.map(any(TodoCreateRequest.class), eq(TodoCreateCommand.class)))
        .thenReturn(todoCreateCommand);
    when(todoService.create(todoCreateCommand)).thenReturn(todo);

    var requestBody = "{\n"
        + "  \"value\": \"value\"\n"
        + "}";
    var expected = "{\n"
        + "  \"id\": 0,\n"
        + "  \"value\": \"value\"\n"
        + "}";
    this.mockMvc
        .perform(post(BASE_PATH).content(requestBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(content().json(expected))
    ;
  }

  @Test
  void getList_success() throws Exception {
    when(todoService.readAll()).thenReturn(List.of(todo));

    var expected = "[{\n"
        + "  \"id\": 0,\n"
        + "  \"value\": \"value\"\n"
        + "}]";
    this.mockMvc
        .perform(get(BASE_PATH))
        .andExpect(status().isOk())
        .andExpect(content().json(expected))
    ;
  }

  @Test
  void put_success() throws Exception {
    var id = 0;
    var todoUpdateCommand = mock(TodoUpdateCommand.class);
    when(modelMapper.map(any(TodoUpdateRequest.class), eq(TodoUpdateCommand.class)))
        .thenReturn(todoUpdateCommand);
    when(todoService.update(todoUpdateCommand)).thenReturn(todo);

    var requestBody = "{\n"
        + "  \"id\": 0,\n"
        + "  \"value\": \"value\"\n"
        + "}";
    var expected = "{\n"
        + "  \"id\": 0,\n"
        + "  \"value\": \"value\"\n"
        + "}";
    this.mockMvc
        .perform(
            put(BASE_PATH + "/" + id).content(requestBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(expected))
    ;
  }

  @Test
  void delete_success() throws Exception {
    var id = 0;
    doNothing().when(todoService).delete(id);

    this.mockMvc
        .perform(delete(BASE_PATH + "/" + id))
        .andExpect(status().isNoContent())
    ;

    verify(todoService).delete(id);
  }
}
