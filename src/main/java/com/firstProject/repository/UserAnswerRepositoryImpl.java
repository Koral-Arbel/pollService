package com.firstProject.repository;

import com.firstProject.model.*;
import com.firstProject.repository.mapper.OptionSelectedMapper;
import com.firstProject.repository.mapper.UserAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserAnswerRepositoryImpl implements UserAnswerRepository {
    private static final String TABLE_NAME_USER_ANSWER = "user_answers";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public UserAnswerResponse createUserAnswer(UserAnswer userAnswer) {
        if (userAnswer != null && userAnswer.getUserId() != null) {
            User user = getUserById(userAnswer.getUserId());
            if (user != null) {
                String sql = "INSERT INTO " + TABLE_NAME_USER_ANSWER + " (user_id, question_id, selected_option_id) VALUES (?, ?, ?)";
                jdbcTemplate.update(sql, user, userAnswer.getQuestionId(), userAnswer.getSelectedOptionId());
                UserAnswerResponse answerResponse = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", UserAnswerResponse.class);
                return answerResponse;
            } else {
                // טפל במקרה שבו המשתמש לא נמצא
                return null; // או משהו אחר בהתאם לדרישות השלב הבא של האפליקציה
            }
        }
        return null;
    }

    @Override
    public void updateUserAnswer(UserAnswer userAnswer) {
        String sql="UPDATE "+ TABLE_NAME_USER_ANSWER +" SET selected_option_id=? WHERE user_id=? AND question_id=?";
        jdbcTemplate.update(sql,
               userAnswer.getSelectedOptionId(),
                userAnswer.getId());
    }

    @Override
    public void deleteUserAnswerById(Long userId) {
        String sql="DELETE FROM "+ TABLE_NAME_USER_ANSWER +" WHERE id=?";
        jdbcTemplate.update(sql, userId);
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
    public User getUserById(Long userId) {
        return getUserById(userId);
    }

}