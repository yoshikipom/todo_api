package com.example.todo.app.controller.exception;


import com.example.todo.app.controller.todo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse badRequest(BadRequestException e) {
    log.info(e.getMessage());
    return ErrorResponse.builder()
        .code(400000)
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse error(Exception e) {
    log.error(e.getMessage(), e);
    return ErrorResponse.builder()
        .code(500000)
        .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
        .build();
  }
}
