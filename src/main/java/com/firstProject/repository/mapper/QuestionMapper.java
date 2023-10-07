package com.firstProject.repository.mapper;

import com.firstProject.model.PollOption;
import com.firstProject.model.PollQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionMapper implements RowMapper <PollQuestion> {

    @Override
    public PollQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        PollQuestion pollQuestion = new PollQuestion(
                rs.getLong("id"),
                rs.getString("title")
        );
        return pollQuestion;
    }
}
