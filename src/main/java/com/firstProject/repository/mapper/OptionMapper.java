package com.firstProject.repository.mapper;

import com.firstProject.model.PollOption;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OptionMapper implements RowMapper<PollOption> {

    @Override
    public PollOption mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("I'm inside the user mapper");
        PollOption pollOption = new PollOption(
        );
        return pollOption;
    }
}
