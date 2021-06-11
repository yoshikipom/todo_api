package com.example.todo.infra.db;


import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TodoMapper {

  @Insert("INSERT INTO todo (value) VALUES (#{value})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(TodoRecord todoRecord);

  @Select("SELECT id, value FROM todo")
  List<TodoRecord> selectAll();

  @Select("SELECT id, value FROM todo WHERE id = (#{id})")
  TodoRecord selectOne(int id);

  @Update("UPDATE todo SET value = #{value} WHERE id = #{id}")
  void update(TodoRecord todoRecord);

  @Delete("DELETE FROM todo WHERE id = #{id}")
  void delete(int id);
}
