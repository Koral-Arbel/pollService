package com.firstProject.repository.mapper;

import com.firstProject.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuestionMapper implements RowMapper <Question> {

    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question(
                rs.getLong("id"),
                rs.getString("title")
        );
        return question;
    }
}
