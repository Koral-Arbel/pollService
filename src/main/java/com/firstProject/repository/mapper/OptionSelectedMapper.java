package com.firstProject.repository.mapper;

import com.firstProject.model.OptionSelectedToMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class OptionSelectedMapper implements RowMapper<OptionSelectedToMapper> {
    @Override
    public OptionSelectedToMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
        OptionSelectedToMapper optionSelectedToMapper = new OptionSelectedToMapper(
                rs.getLong("selected_option_id"),
                rs.getInt("times_answered")
        );
        return optionSelectedToMapper;
    }
}
