package com.firstProject.repository.mapper;

import com.firstProject.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("I'm inside the user mapper User POLL SYSTEM");
        User user = new User(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getInt("age"),
                rs.getString("address"),
                rs.getTimestamp("join_date").toLocalDateTime(),
                rs.getBoolean("registered")
        );
        return user;
    }
}
