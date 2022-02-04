package com.example.todo.infra.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class TodoMapperTest {

  @Autowired
  private TodoMapper todoMapper;

  @Test
  void insert_success() {
    var value = "insert-test";
    var todoRecord = TodoRecord.builder()
        .value(value)
        .build();

    todoMapper.insert(todoRecord);

    var actual = todoMapper.selectAll();
    assertEquals(1, actual.size());
    assertEquals(value, actual.get(0).getValue());
  }

  @Test
  @Sql(scripts = {"classpath:/infra/db/todo-select.sql"})
  void selectAll_success() {
    var actual = todoMapper.selectAll();

    assertEquals(1, actual.size());
    assertEquals("select-test", actual.get(0).getValue());
  }

  @Test
  @Sql(scripts = {"classpath:/infra/db/todo-select.sql"})
  void selectOne_success() {
    var actual = todoMapper.selectOne(1);

    assertEquals("select-test", actual.getValue());
  }

  @Test
  @Sql(scripts = {"classpath:/infra/db/todo-update.sql"})
  void update_success() {
    var value = "updated";
    var todoRecord = TodoRecord.builder()
        .id(1)
        .value(value)
        .build();

    todoMapper.update(todoRecord);

    var actual = todoMapper.selectOne(1);
    assertEquals(value, actual.getValue());
  }

  @Test
  @Sql(scripts = {"classpath:/infra/db/todo-delete.sql"})
  void delete_success() {
    todoMapper.delete(1);

    var actual = todoMapper.selectOne(1);
    assertNull(actual);
  }
}
