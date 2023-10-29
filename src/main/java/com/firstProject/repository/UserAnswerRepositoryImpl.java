package com.firstProject.repository;

import com.firstProject.model.*;
import com.firstProject.repository.mapper.OptionSelectedMapper;
import com.firstProject.repository.mapper.UserAnswerMapper;
import com.firstProject.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserAnswerRepositoryImpl implements UserAnswerRepository {
    private static final String TABLE_NAME_USER_ANSWER = "user_answers";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserAnswerResponse createUserAnswer(UserAnswer userAnswer) {
        String sql = "INSERT INTO " + TABLE_NAME_USER_ANSWER + " (user_id, question_id, selected_option_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, userAnswer.getUserId(), userAnswer.getQuestionId(), userAnswer.getSelectedOptionId());
        Long lastInsertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        UserAnswerResponse answerResponse = new UserAnswerResponse();
        answerResponse.setId(lastInsertedId);
        System.out.println("User answer saved in the database");
        return answerResponse;
    }

    @Override
    public void updateUserAnswer(UserAnswer userAnswer) {
        String sql= "UPDATE " + TABLE_NAME_USER_ANSWER +" SET selected_option_id=? WHERE user_id=? AND question_id=?";
        jdbcTemplate.update(sql, userAnswer.getSelectedOptionId(), userAnswer.getId(), userAnswer.getQuestionId());
    }

    @Override
    public void deleteUserAnswerById(Long userId) {
        String sql= "DELETE FROM " + TABLE_NAME_USER_ANSWER + " WHERE id=?";
        jdbcTemplate.update(sql, userId);
    }
    @Override
    public List<SelectedOptionToMapper> getUsersChoseQuestionOptionNumber(Long questionId) {
        String sql = "SELECT selected_option_id, COUNT(selected_option_id) AS amount_answers_answered FROM " + TABLE_NAME_USER_ANSWER + " WHERE question_id = ? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql, new OptionSelectedMapper(), questionId);
    }
    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        String sql= "SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE question_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,questionId);
    }

    @Override
    public List<UserAnswer> getAllUserAnswers(Long userId) {
        String sql= "SELECT * FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.query(sql,new UserAnswerMapper(),userId);
    }

    @Override
    public Integer getNumberOfQuestionsUserAnswered(Long userId) {
        String sql= "SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,userId);
    }

    @Override
    public List<SelectedOptionToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId) {
        String sql= "SELECT  selected_option_id, COUNT(selected_option_id) as amount_answers_answered FROM "+ TABLE_NAME_USER_ANSWER + " WHERE question_id=? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql,new OptionSelectedMapper(),questionId);
    }

    @Override
    public Boolean hasUserAnsweredQuestion(Long userId, Long questionId) {
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME_USER_ANSWER + " WHERE user_id=? AND question_id=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userId, questionId);
        return count > 0;
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM " + TABLE_NAME_USER_ANSWER + " WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), userId);
    }
}