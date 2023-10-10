package com.firstProject.repository;

import com.firstProject.model.OptionSelectedToMapper;
import com.firstProject.model.UserAnswer;
import com.firstProject.model.UserAnswerResponse;
import com.firstProject.repository.mapper.OptionSelectedMapper;
import com.firstProject.repository.mapper.UserAnswerMapper;
import com.firstProject.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAnswerRepositoryImpl implements UserAnswerRepository {
    private static final String TABLE_NAME_USER_ANSWER = "user_answers";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void createUserAnswer(UserAnswer userAnswer) {
        String insertSql = "INSERT INTO " + TABLE_NAME_USER_ANSWER + " (user_id, question_id, selected_option_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertSql,
                userAnswer.getUserId(),
                userAnswer.getQuestionId(),
                userAnswer.getSelectedOptionId());

        String selectSql = "SELECT id FROM " + TABLE_NAME_USER_ANSWER + " WHERE user_id = ? AND question_id = ? AND selected_option_id = ?";
        Long id = jdbcTemplate.queryForObject(selectSql, Long.class,
                userAnswer.getUserId(),
                userAnswer.getQuestionId(),
                userAnswer.getSelectedOptionId());

    }

    @Override
    public void updateUserAnswer(UserAnswer userAnswer) {
        String sql="UPDATE "+ TABLE_NAME_USER_ANSWER +" SET selected_option_id = ? WHERE id=?";
        jdbcTemplate.update(sql,
                userAnswer.getSelectedOptionId(),
                userAnswer.getId());
    }

    @Override
    public void deleteUserAnswer(Long id) {
        String sql="DELETE FROM "+ TABLE_NAME_USER_ANSWER +" WHERE id=?";
        jdbcTemplate.update(sql,
                id);
    }

    @Override
    public void deleteQuestionAnswerByUserId(Long userId) {
        String sql="DELETE FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        jdbcTemplate.update(sql, userId);

    }

    @Override
    public Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId, Long questionId) {
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

    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        String sql="SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE question_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,questionId);
    }

    @Override
    public List<OptionSelectedToMapper> getUsersChoseQuestionOptionNumber(Long questionId) {
        String sql="SELECT question_id, selected_option_id, COUNT(selected_option_id) AS times_answered From "+ TABLE_NAME_USER_ANSWER + " Where question_id=? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql,new OptionSelectedMapper(),questionId);
    }

    @Override
    public Integer getNumberOfQuestionsUserAnswered(Long userId) {
        String sql="SELECT COUNT(id) FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,userId);
    }

    @Override
    public List<OptionSelectedToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId) {
        String sql="SELECT  selected_option_id, COUNT(selected_option_id) AS times_answered FROM "+ TABLE_NAME_USER_ANSWER +" WHERE question_id=? GROUP BY selected_option_id";
        return jdbcTemplate.query(sql,new OptionSelectedMapper(),questionId);
    }

    @Override
    public List<UserAnswer> getAllUserAnswers(Long userId) {
        String sql="SELECT * FROM "+ TABLE_NAME_USER_ANSWER +" WHERE user_id=?";
        return jdbcTemplate.query(sql,new UserAnswerMapper(),userId);
    }

}