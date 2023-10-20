package com.firstProject.repository;

import com.firstProject.model.SelectedOptionToMapper;
import com.firstProject.model.User;
import com.firstProject.model.UserAnswer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerRepository {
    void createUserAnswer(UserAnswer userAnswer);
    void updateUserAnswer(UserAnswer userAnswer);
    void deleteUserAnswer(Long id);
    void deleteQuestionAnswerByUserId(Long userId);

    ResponseEntity<User> getUserByEmail(String email);


    boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);
    Integer getUsersAnsweredCountByQuestionId(Long questionId);
    List<SelectedOptionToMapper> getUsersChoseQuestionOptionNumber(Long questionId);
    Integer getNumberOfQuestionsUserAnswered(Long userId);
    List<SelectedOptionToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId);
    List<UserAnswer> getAllUserAnswers(Long userId);

}
