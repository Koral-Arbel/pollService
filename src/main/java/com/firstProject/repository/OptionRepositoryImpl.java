package com.firstProject.repository;

import com.firstProject.model.Option;
import com.firstProject.repository.mapper.OptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionRepositoryImpl implements OptionRepository {
    private static final String TABLE_NAME_OPTION = "poll_options";
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public Long createOption(Option option) {
        String sql="INSERT INTO " + TABLE_NAME_OPTION + "(answer, question_id) VALUES(?,?)";
        jdbcTemplate.update(sql,option.getTextOption(),option.getQuestionId());
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
    }

    @Override
    public void updateOption(Option option) {
        String sql="UPDATE " + TABLE_NAME_OPTION +" SET answer=? WHERE id=?";
        jdbcTemplate.update(sql,option.getTextOption(),option,option.getId());
    }

    @Override
    public void deleteOptionFromAnswer(Long id) {
        String sql = "DELETE FROM " + TABLE_NAME_OPTION + " WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Option getOptionById(Long id) {
        String sql="SELECT * FROM " + TABLE_NAME_OPTION +" WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql,new OptionMapper(),id);
        }catch (EmptyResultDataAccessException exception){
            System.out.println("Empty");
            return null;
        }
    }

    @Override
    public List<Option> getOptionsByQuestionId(Long questionId) {
        String sql="Select * From "+ TABLE_NAME_OPTION +" WHERE question_id=?";
        try {
            return jdbcTemplate.query(sql,new OptionMapper(),questionId);
        }catch (EmptyResultDataAccessException exception){
            System.out.println("Empty");
            return null;
        }
    }
}
