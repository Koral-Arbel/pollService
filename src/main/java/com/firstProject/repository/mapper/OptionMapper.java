package com.firstProject.repository.mapper;

import com.firstProject.model.Option;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OptionMapper implements RowMapper<Option> {


    @Override
    public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
        Option option = new Option(
                rs.getLong("id"),
                rs.getString("option"),
                rs.getLong("question_id"));
        return option;
    }
}

