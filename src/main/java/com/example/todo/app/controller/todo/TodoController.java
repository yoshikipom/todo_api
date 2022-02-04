package com.example.todo.app.controller.todo;

import static java.util.stream.Collectors.toList;

import com.example.todo.app.controller.exception.BadRequestException;
import com.example.todo.domain.model.Todo;
import com.example.todo.domain.model.TodoCreateCommand;
import com.example.todo.domain.model.TodoUpdateCommand;
import com.example.todo.domain.service.TodoService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

  private final TodoService todoService;
  private final ModelMapper modelMapper;

  public TodoController(TodoService todoService, ModelMapper modelMapper) {
    this.todoService = todoService;
    this.modelMapper = modelMapper;
  }

  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public TodoResponse post(@RequestBody TodoCreateRequest todoCreateRequest) {
    TodoCreateCommand todoCreateCommand = modelMapper
        .map(todoCreateRequest, TodoCreateCommand.class);
    Todo todo = todoService.create(todoCreateCommand);
    return modelMapper.map(todo, TodoResponse.class);
  }

  @GetMapping("")
  public List<TodoResponse> getList() {
    List<Todo> todoList = todoService.readAll();
    return todoList.stream()
        .map(todo -> modelMapper.map(todo, TodoResponse.class))
        .collect(toList());
  }

  @PutMapping("{id}")
  public TodoResponse put(@PathVariable("id") Integer id,
      @RequestBody TodoUpdateRequest todoUpdateRequest) {
    if (id != todoUpdateRequest.getId()) {
      throw new BadRequestException("id in path is different from id in body");
    }

    TodoUpdateCommand todoUpdateCommand = modelMapper
        .map(todoUpdateRequest, TodoUpdateCommand.class);
    Todo todo = todoService.update(todoUpdateCommand);
    return modelMapper.map(todo, TodoResponse.class);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Integer id) {
    todoService.delete(id);
  }
}
