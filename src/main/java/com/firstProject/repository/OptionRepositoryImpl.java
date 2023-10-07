package com.firstProject.repository;

import com.firstProject.model.PollOption;
import com.firstProject.model.UserAnswer;
import com.firstProject.repository.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepositoryImpl implements OptionRepository {
    private static final String TABLE_NAME_OPTION = "option_question";
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Long createOption(UserAnswer userAnswer) {
        String sql = "INSERT INTO " + TABLE_NAME_OPTION + "(option, question_id) Values(?,?)";
        jdbcTemplate.update(sql, userAnswer.getOption(), userAnswer.getQuestion());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }


    @Override
    public void updateOption(UserAnswer userAnswer) {
        String sql = "Update " + TABLE_NAME_OPTION + " SET option=? WHERE id=?";
        jdbcTemplate.update(sql, userAnswer.getOption(), userAnswer, userAnswer.getId());
    }


    @Override
    public void deleteOptionFromAnswer(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME_OPTION + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public PollOption getOptionById(Long id) {
        String sql = "SELECT * FROM " + TABLE_NAME_OPTION + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new OptionMapper(), id);
        } catch (EmptyResultDataAccessException exception) {
            System.out.println("Null");
            return null;
        }
    }

    @Override
    public List<PollOption> getOptionsByQuestionId(Long questionId) {
        String sql = "SELECT * FROM " + TABLE_NAME_OPTION + " WHERE question_id=?";
        try {
            return jdbcTemplate.query(sql, new OptionMapper(), questionId);
        } catch (EmptyResultDataAccessException exception) {
            System.out.println("Null");
            return null;
        }
    }
}
