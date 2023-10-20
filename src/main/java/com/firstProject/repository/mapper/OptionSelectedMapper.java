package com.firstProject.repository.mapper;

import com.firstProject.model.SelectedOptionToMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class OptionSelectedMapper implements RowMapper<SelectedOptionToMapper> {
    @Override
    public SelectedOptionToMapper mapRow(ResultSet rs, int rowNum) throws SQLException {
        SelectedOptionToMapper selectedOptionToMapper = new SelectedOptionToMapper(
                rs.getLong("selected_option_id"),
                rs.getInt("amount_answers_answered")
        );
        return selectedOptionToMapper;
    }
}
