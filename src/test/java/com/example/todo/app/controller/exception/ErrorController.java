package com.example.todo.app.controller.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {

  @GetMapping("badrequest")
  public void badRequest() {
    throw new BadRequestException("error");
  }

  @GetMapping("error")
  public void error() {
    throw new RuntimeException("error");
  }
}
