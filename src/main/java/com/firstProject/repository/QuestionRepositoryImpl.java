package com.firstProject.repository;

import com.firstProject.model.PollQuestion;
import com.firstProject.repository.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class QuestionRepositoryImpl implements QuestionRepository{
    private static final String TABLE_NAME_QUESTION = "question";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Long createQuestion(PollQuestion question) {
        String sql = "INSERT INTO " + TABLE_NAME_QUESTION + " (question) VALUES (?)";
        long generatedKey = -1; // Initialize with a default value

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, question.getTitle());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedKey = generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            // Handle any exceptions here
            e.printStackTrace();
        }

        return generatedKey;
    }

    @Override
    public PollQuestion updateQuestion(PollQuestion question) {
        String sql="UPDATE " + TABLE_NAME_QUESTION +" SET question=? WHERE id=?";
        jdbcTemplate.update(sql,question.getTitle(),question.getId());
        return null;
    }

    @Override
    public void DeleteQuestion(Long  questionId) {
        String sql="DELETE FROM " + TABLE_NAME_QUESTION + " WHERE id=?";
        jdbcTemplate.update(sql,questionId);
    }

    @Override
    public PollQuestion getQuestionById(Long id) {
        String sql="SELECT * FROM "+ TABLE_NAME_QUESTION +" WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql,new QuestionMapper(),id);
        }catch (EmptyResultDataAccessException exception){
            System.out.println("Null");
            return null;
        }
    }

    @Override
    public List<PollQuestion> getQuestionsList() {
        String sql="SELECT * FROM "+ TABLE_NAME_QUESTION;
        try {
            return jdbcTemplate.query(sql,new QuestionMapper());
        }catch (EmptyResultDataAccessException exception){
            return null;
        }
    }
}
