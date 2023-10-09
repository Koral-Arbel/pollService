package com.firstProject.repository.mapper;

import com.firstProject.model.UserAnswer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserAnswerMapper implements RowMapper<UserAnswer> {
    @Override
    public UserAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserAnswer userAnswer = new UserAnswer(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("question_id"),
                rs.getLong("selected_option_id")
        );
        return userAnswer;
    }
}
