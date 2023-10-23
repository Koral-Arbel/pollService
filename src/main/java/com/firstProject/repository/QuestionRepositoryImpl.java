package com.firstProject.repository;

import com.firstProject.model.Question;
import com.firstProject.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private static final String TABLE_NAME_QUESTION = "poll_question";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long createQuestion(Question question) {
        String sql = "INSERT INTO " + TABLE_NAME_QUESTION + "(title) VALUES(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "ID" });
            ((PreparedStatement) ps).setString(1, question.getTitle());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Question updateQuestion(Question question) {
        String sql="UPDATE "+ TABLE_NAME_QUESTION +" SET title=? WHERE id=?";
        jdbcTemplate.update(sql,question.getTitle(),question.getId());
        return null;
    }

    public void deleteQuestion(Long id) {
        String sql="DELETE FROM "+ TABLE_NAME_QUESTION + " WHERE id=?";
        jdbcTemplate.update(sql,id);

    }

    @Override
    public Question getQuestionById(Long id) {
        String sql="SELECT * FROM "+ TABLE_NAME_QUESTION + " WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql,new QuestionMapper(),id);
        }catch (EmptyResultDataAccessException exception){
            System.out.println("Empty");
            return null;
        }
    }

    @Override
    public List<Question> getAllPoll() {
        String sql="SELECT * FROM "+ TABLE_NAME_QUESTION;
        try {
            return jdbcTemplate.query(sql,new QuestionMapper());
        }catch (EmptyResultDataAccessException exception){
            return null;
        }
    }
}