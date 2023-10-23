package com.firstProject.repository;

import com.firstProject.model.SelectedOptionToMapper;
import com.firstProject.model.User;
import com.firstProject.model.UserAnswer;
import com.firstProject.model.UserAnswerRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerRepository {
    Long createUserAnswer(UserAnswer userAnswer);
    void updateUserAnswer(UserAnswer userAnswer);
    void deleteUserAnswerById(Long id);
    List<SelectedOptionToMapper> getUsersChoseQuestionOptionNumber(Long questionId);
    Integer getUsersAnsweredCountByQuestionId(Long questionId);
    List<UserAnswer> getAllUserAnswers(Long userId);
    Integer getNumberOfQuestionsUserAnswered(Long userId);
    List<SelectedOptionToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId);


    ResponseEntity<User> getUserByEmail(String email);
    boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);

}
