package com.example.demo.repository;

import com.example.demo.domain.User;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SimpleJdbcCall simpleJdbcCall;

  /*
  public List<User> findAll() {
    List<User> result = jdbcTemplate.query(
      "SELECT id, name, age FROM user_list",
      (rs, rowNum) ->
        new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"))
    );

    return result;
  }
  */

  public List<User> findAll() {
    simpleJdbcCall =
      new SimpleJdbcCall(jdbcTemplate)
      .withProcedureName("CRUD_SELECT_USER_LIST");

    Map<String, Object> result = simpleJdbcCall
      .returningResultSet("user", BeanPropertyRowMapper.newInstance(User.class))
      .execute();
    List<User> users = (List<User>) result.get("user");

    return users;
  }

  /*
  public User findById(Integer id) {
    return jdbcTemplate.queryForObject(
      "SELECT id, name, age FROM user_list WHERE id = ?",
      BeanPropertyRowMapper.newInstance(User.class),
      new Object[] { id }
    );
  }
  */

  public User findById(Integer id) {
    simpleJdbcCall =
      new SimpleJdbcCall(jdbcTemplate)
      .withProcedureName("CRUD_SELECT_USER_LIST_BY_ID");

    MapSqlParameterSource inParams = new MapSqlParameterSource();
    inParams.addValue("ID", id);

    Map<String, Object> result = simpleJdbcCall
      .returningResultSet("user", BeanPropertyRowMapper.newInstance(User.class))
      .execute(inParams);
    List<User> users = (List<User>) result.get("user");
    User user = (User) users.get(0);

    return user;
  }

  public User save(User user) {
    simpleJdbcCall =
      new SimpleJdbcCall(jdbcTemplate)
      .withProcedureName("CRUD_INSERT_USER_LIST");

    MapSqlParameterSource inParams = new MapSqlParameterSource();
    inParams.addValue("NAME", user.getName());
    inParams.addValue("AGE", user.getAge());

    Map<String, Object> result = simpleJdbcCall
      .returningResultSet("user", BeanPropertyRowMapper.newInstance(User.class))
      .execute(inParams);
    List<User> users = (List<User>) result.get("user");
    User createdUser = (User) users.get(0);

    return createdUser;
  }

  public User update(User original, User user) {
    simpleJdbcCall =
      new SimpleJdbcCall(jdbcTemplate)
      .withProcedureName("CRUD_UPDATE_USER_LIST");

    MapSqlParameterSource inParams = new MapSqlParameterSource();
    inParams.addValue("NAME", user.getName());
    inParams.addValue("AGE", user.getAge());
    inParams.addValue("Original_ID", original.getId());
    inParams.addValue("Original_NAME", original.getName());
    inParams.addValue("Original_AGE", original.getAge());
    inParams.addValue("ID", user.getId());

    Map<String, Object> result = simpleJdbcCall
      .returningResultSet("user", BeanPropertyRowMapper.newInstance(User.class))
      .execute(inParams);
    List<User> users = (List<User>) result.get("user");
    User updatedUser = (User) users.get(0);

    return updatedUser;
  }

  public void delete(User user) {
    simpleJdbcCall =
      new SimpleJdbcCall(jdbcTemplate)
      .withProcedureName("CRUD_DELETE_USER_LIST");

    MapSqlParameterSource inParams = new MapSqlParameterSource();
    inParams.addValue("Original_ID", user.getId());
    inParams.addValue("Original_NAME", user.getName());
    inParams.addValue("Original_AGE", user.getAge());

    simpleJdbcCall.execute(inParams);
  }
}
