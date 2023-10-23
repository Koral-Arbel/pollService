package com.firstProject.repository;

import com.firstProject.model.*;
import com.firstProject.repository.mapper.OptionSelectedMapper;
import com.firstProject.repository.mapper.UserAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Long createUserAnswer(UserAnswer userAnswer) {
        String sql = "INSERT INTO " + TABLE_NAME_USER_ANSWER + " " + "(user_id, question_id, selected_option_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, userAnswer.getUserId());
                    ps.setLong(2, userAnswer.getQuestionId());
                    ps.setLong(3, userAnswer.getSelectedOptionId());
                    return ps;
                },
                keyHolder
        );
        return (Long) keyHolder.getKey();
    }
    @Override
    public void updateUserAnswer(UserAnswer userAnswer) {
        String sql="UPDATE "+ TABLE_NAME_USER_ANSWER +" SET selected_option_id=? WHERE id=?";
        jdbcTemplate.update(sql,
                userAnswer.getSelectedOptionId(),
                userAnswer.getId());
    }

    @Override
    public void deleteUserAnswerById(Long id) {
        String sql="DELETE FROM "+ TABLE_NAME_USER_ANSWER +" WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
    @Override
    public List<SelectedOptionToMapper> getUsersChoseQuestionOptionNumber(Long questionId) {
        String sql="SELECT question_id, selected_option_id, COUNT(selected_option_id) AS times_answered From "+ TABLE_NAME_USER_ANSWER + " Where question_id=? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql,new OptionSelectedMapper(),questionId);
    }
    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        String sql="SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE question_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,questionId);
    }

    @Override
    public List<UserAnswer> getAllUserAnswers(Long userId) {
        String sql="SELECT * FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.query(sql,new UserAnswerMapper(),userId);
    }

    @Override
    public Integer getNumberOfQuestionsUserAnswered(Long userId) {
        String sql="SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,userId);
    }

    @Override
    public List<SelectedOptionToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId) {
        String sql="SELECT  selected_option_id, COUNT(selected_option_id) AS times_answered FROM "+ TABLE_NAME_USER_ANSWER +" WHERE question_id=? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql,new OptionSelectedMapper(),questionId);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        return null;
    }
    @Override
    public boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId, Long questionId) {
        String sql="SELECT * FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=? AND question_id=?";
        try{
            UserAnswer userAnswer =jdbcTemplate.queryForObject(sql,
                    new UserAnswerMapper(),
                    userId,
                    questionId);
            if(userAnswer !=null){
                return true;
            }
        }catch (Exception err){
            return false;
        }
        return false;
    }

}